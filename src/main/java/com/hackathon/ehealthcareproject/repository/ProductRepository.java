package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.dto.product.ProductRequestDto;
import com.hackathon.ehealthcareproject.dto.product.ProductResponseDto;
import com.hackathon.ehealthcareproject.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
