package com.pankajproduct.inventory_service.controller;


import com.pankajproduct.inventory_service.dto.InventoryResponse;
import com.pankajproduct.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //list of skucode
    //http://localhost:8084/api/inventory/iphone-13,iphone-13-red as PathVariables
    //http://localhost:8084/api/inventory?skuCode=iphone-13&skuCode=iphone-13-red as requestParam
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("sku-code") String skuCode ){
//        return inventoryService.isInStock(skuCode);
//    }
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode ){
        return inventoryService.isInStock(skuCode);
    }
}
