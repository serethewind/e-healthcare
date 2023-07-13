package com.hackathon.ehealthcareproject.service.products;

import com.hackathon.ehealthcareproject.dto.product.ProductRequestDto;
import com.hackathon.ehealthcareproject.dto.product.ProductResponseDto;
import com.hackathon.ehealthcareproject.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    List<ProductResponseDto> viewAllProducts();
    ProductResponseDto viewSingleProduct(long id);
    ProductResponseDto updateSingleProduct(long id, ProductRequestDto productRequestDto);

    List<ProductResponseDto> fetchProductsByName(String productName);

    List<ProductResponseDto> fetchAvailableProducts();

    void deleteProduct(long id);
}
