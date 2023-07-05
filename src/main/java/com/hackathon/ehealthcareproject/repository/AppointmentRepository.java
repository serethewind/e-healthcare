package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}
