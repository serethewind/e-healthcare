package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
