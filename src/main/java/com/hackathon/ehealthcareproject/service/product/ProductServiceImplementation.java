package com.hackathon.ehealthcareproject.service.product;

import com.hackathon.ehealthcareproject.dto.product.Data;
import com.hackathon.ehealthcareproject.dto.product.ProductRequest;
import com.hackathon.ehealthcareproject.dto.product.ProductResponse;
import com.hackathon.ehealthcareproject.entity.ProductEntity;
import com.hackathon.ehealthcareproject.repository.repository.ProductRepository;
import com.hackathon.ehealthcareproject.utils.product.Utils;
import com.hackathon.ehealthcareproject.utils.staff.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class ProductServiceImplementation implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        if (productRepository.existsByProductName(productRequest.getProductName())){
            return ProductResponse.builder()
                    .responseCode(Utils.EXISTED_RESPONSE_CODE)
                    .responseMessage(Utils.EXISTED_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        ProductEntity newProduct = ProductEntity.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .isAvailable(productRequest.getIsAvailable())
                .imageUris(productRequest.getImageUris())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .build();
        ProductEntity savedProduct = productRepository.save(newProduct);

        return ProductResponse.builder()
                .responseCode(Utils.REGISTERED_RESPONSE_CODE)
                .responseMessage(Utils.REGISTERED_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .productName(savedProduct.getProductName())
                        .build())
                .build();
    }

    @Override
    public List<ProductEntity> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponse viewSingleProduct(String productName) {
        if (!productRepository.existsByProductName(productName)){
            return ProductResponse.builder()
                    .responseCode(Utils.PRODUCT_NOT_EXISTS_RESPONSE_CODE)
                    .responseMessage(Utils.PRODUCT_NOT_EXISTS_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
         productRepository.findByProductName(productName);
        return ProductResponse.builder()
                .responseCode(Utils.FOUND_RESPONSE_CODE)
                .responseMessage(Utils.FOUND_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .productName(productName)
                        .build())
                .build();
    }

    @Override
    public ProductResponse updateProducts(ProductRequest productRequest) {
        if (!productRepository.existsByProductName(productRequest.getProductName())){
            return ProductResponse.builder()
                    .responseCode(Utils.NOT_FOUND_RESPONSE_CODE)
                    .responseMessage(Utils.NOT_FOUND_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        ProductEntity newProduct = ProductEntity.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .isAvailable(productRequest.getIsAvailable())
                .imageUris(productRequest.getImageUris())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .build();
        ProductEntity savedProduct = productRepository.save(newProduct);

        return ProductResponse.builder()
                .responseCode(Utils.REGISTERED_RESPONSE_CODE)
                .responseMessage(Utils.REGISTERED_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .productName(savedProduct.getProductName())
                        .build())
                .build();
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        while (productEntity.getIsAvailable().equals(false)) {
            productEntity.setIsAvailable(true);
        }
        ProductEntity active = productRepository.save(productEntity);
        return ProductResponse.builder()
                .responseCode(ResponseUtils.REACTIVATION_RESPONSE_CODE)
                .responseMessage(ResponseUtils.REACTIVATION_RESPONSE_MESSAGE)
                .data((lombok.Data) com.hackathon.ehealthcareproject.dto.staff.Data.builder()
                        .name(active.getProductName())
                        .build())
                .build();
    }
}
