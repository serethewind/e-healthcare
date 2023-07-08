package com.hackathon.ehealthcareproject.service.product;

import com.hackathon.ehealthcareproject.dto.product.ProductRequest;
import com.hackathon.ehealthcareproject.dto.product.ProductResponse;
import com.hackathon.ehealthcareproject.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    List<ProductEntity> listAllProducts();
    ProductResponse viewSingleProduct(String productName);
    ProductResponse updateProducts(ProductRequest productRequest);
    ProductResponse deleteProduct(Long id);
}
