package com.hackathon.ehealthcareproject.service.products;

import com.hackathon.ehealthcareproject.dto.product.ProductRequestDto;
import com.hackathon.ehealthcareproject.dto.product.ProductResponseDto;
import com.hackathon.ehealthcareproject.entity.ProductEntity;
import com.hackathon.ehealthcareproject.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        ProductEntity newProduct = ProductEntity.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .isAvailable(true)
                .imageUris(productRequestDto.getImageUris())
                .description(productRequestDto.getDescription())
                .quantity(productRequestDto.getQuantity())
                .build();
        productRepository.save(newProduct);

        return ProductResponseDto.builder()
                .productId(newProduct.getId())
                .productName(newProduct.getProductName())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .quantity(newProduct.getQuantity())
                .imageUris(newProduct.getImageUris())
                .build();
    }

    @Override
    public List<ProductResponseDto> viewAllProducts() {
        return productRepository.findAll().stream().map(productEntity -> ProductResponseDto.builder()
                .productId(productEntity.getId())
                .productName(productEntity.getProductName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .imageUris(productEntity.getImageUris())
                .build()).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto viewSingleProduct(long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ProductResponseDto.builder()
                .productId(productEntity.getId())
                .productName(productEntity.getProductName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .imageUris(productEntity.getImageUris())
                .build();
    }

    @Override
    public ProductResponseDto updateSingleProduct(long id, ProductRequestDto productRequestDto) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productEntity.setProductName(productRequestDto.getProductName());
        productEntity.setPrice(productRequestDto.getPrice());
        productEntity.setImageUris(productRequestDto.getImageUris());
        productEntity.setDescription(productRequestDto.getDescription());
        productEntity.setQuantity(productRequestDto.getQuantity());


        productRepository.save(productEntity);
        return ProductResponseDto.builder()
                .productId(productEntity.getId())
                .productName(productEntity.getProductName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .imageUris(productEntity.getImageUris())
                .build();
    }

    @Override
    public List<ProductResponseDto> fetchProductsByName(String productName) {


        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().filter(product -> product.getProductName().toLowerCase().contains(productName.toLowerCase()))
                .map(productEntity -> ProductResponseDto.builder()
                        .productId(productEntity.getId())
                        .productName(productEntity.getProductName())
                        .description(productEntity.getDescription())
                        .price(productEntity.getPrice())
                        .quantity(productEntity.getQuantity())
                        .imageUris(productEntity.getImageUris())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> fetchAvailableProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().filter(product -> product.getIsAvailable()).map(productEntity -> ProductResponseDto.builder()
                .productId(productEntity.getId())
                .productName(productEntity.getProductName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .imageUris(productEntity.getImageUris())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        while (productEntity.getIsAvailable().equals(true)) {
            productEntity.setIsAvailable(false);
            productRepository.save(productEntity);
        }
    }

}
