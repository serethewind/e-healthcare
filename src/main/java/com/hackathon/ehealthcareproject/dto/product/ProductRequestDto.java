package com.hackathon.ehealthcareproject.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;
    private Boolean isAvailable = true;
    private String imageUris;
}
