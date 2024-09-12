package com.pankajproduct.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductResponse {
//not expose the product entity to the outer world

    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
