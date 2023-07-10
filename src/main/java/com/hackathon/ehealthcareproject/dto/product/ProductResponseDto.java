package com.hackathon.ehealthcareproject.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;
    private String imageUris;
}