package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
