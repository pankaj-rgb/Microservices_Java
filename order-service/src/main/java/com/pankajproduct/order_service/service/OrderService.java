package com.pankajproduct.order_service.service;

import com.pankajproduct.order_service.dto.InventoryResponse;
import com.pankajproduct.order_service.dto.OrderLineItemsDto;
import com.pankajproduct.order_service.dto.OrderRequest;
import com.pankajproduct.order_service.event.OrderPlacedEvent;
import com.pankajproduct.order_service.model.Order;
import com.pankajproduct.order_service.model.OrderLineItems;
import com.pankajproduct.order_service.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    //for observation we are using micrometer
//    private final ObservationRegistry observationRegistry;

    //for kafka template
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    private final OrderRepository orderRepository;
    //private final WebClient webClient; //for synchronous call
    private final WebClient.Builder webClientBuilder;
    public String placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems= orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        //call inventory service and place order if product is in stock

        order.setOrderLineItemsList(orderLineItems);
        List<String> skuCodes= order.getOrderLineItemsList().stream()
                                .map(OrderLineItems::getSkuCode).toList();

        //call inventory service and place order if product is in stock( Inventory Service Application)

       InventoryResponse[] inventoryResponseArray=
//               webClient.get()
        webClientBuilder.build().get()
               .uri(
//                       "http://localhost:8084/api/inventory",
                       //now our application try to fetch the details from the eureka server about the application
                       "http://inventory-service/api/inventory",
                       uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                                .block();

       //list of response , chekcing all match whether all elements include true , if any false give false
        boolean allProductInStock=Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
       if(allProductInStock) {
           orderRepository.save(order);
           kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
           return "order placed successfully";
       }
       else {
           throw new IllegalArgumentException("product is not in stock ");
       }

    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
