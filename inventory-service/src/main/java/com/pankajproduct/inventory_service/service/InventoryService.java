package com.pankajproduct.inventory_service.service;

import com.pankajproduct.inventory_service.dto.InventoryResponse;
import com.pankajproduct.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @SneakyThrows //for demo purpose not to be in production code
    public  List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("wait started");
        Thread.sleep(10000);
        log.info("wait ended");
          return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory ->
                 InventoryResponse.builder().skuCode(inventory.getSkuCode())
                         .isInStock(inventory.getQuantity()>0).build()).toList();
    }
}
