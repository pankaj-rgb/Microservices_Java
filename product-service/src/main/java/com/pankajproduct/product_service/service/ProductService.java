package com.pankajproduct.product_service.service;

import com.pankajproduct.product_service.dto.ProductRequest;
import com.pankajproduct.product_service.dto.ProductResponse;
import com.pankajproduct.product_service.model.Product;
import com.pankajproduct.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        //cretae the object of the product
        Product product=Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
      log.info("product {} is saved ",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
    List<Product> products=productRepository.findAll();
    return products.stream().map(this::mapToProductResponse).toList();
    }
    public ProductResponse mapToProductResponse(Product product){

        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
