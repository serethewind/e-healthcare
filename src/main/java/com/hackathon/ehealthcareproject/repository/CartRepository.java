package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.entity.Cart;
import com.hackathon.ehealthcareproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser (UserEntity user);
}
