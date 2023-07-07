package com.hackathon.ehealthcareproject.repository.doctorRepository;

import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Boolean existsByEmail(String email);
    DoctorEntity findByEmail(String email);
    Boolean existsByIsAvailable(Boolean isAvailable);
    DoctorEntity findByIsAvailable(Boolean isAvailable);
}
