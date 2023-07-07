package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByUserEntity_Username(String username);


}
