package com.hackathon.ehealthcareproject.repository;

import com.hackathon.ehealthcareproject.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
}
