package com.hackathon.ehealthcareproject.repository.repository;

import com.hackathon.ehealthcareproject.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Boolean existsByProductName(String productName);
    Boolean findByProductName(String productName);
    Boolean existsByIsAvailable(Boolean isAvailable);
    Boolean findByIsAvailable(Boolean isAvailable);
}
