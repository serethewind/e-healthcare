package com.hackathon.ehealthcareproject.repository;


import com.hackathon.ehealthcareproject.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    RolesEntity findByName(String name);
}
