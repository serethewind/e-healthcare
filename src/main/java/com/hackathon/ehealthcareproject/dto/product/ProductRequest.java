package com.hackathon.ehealthcareproject.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;
    private Boolean isAvailable = true;
    private String imageUris;
}
