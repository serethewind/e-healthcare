package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    @Query("select u from UserEntity u where u.username = ?1")
    Optional<UserEntity> findUserByUsername(String username);



}
