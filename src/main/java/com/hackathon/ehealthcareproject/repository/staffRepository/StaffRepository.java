package com.hackathon.ehealthcareproject.repository.staffRepository;

import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    Boolean existsByEmail(String email);
    StaffEntity findByEmail(String email);
    Boolean existsByIsAvailable(Boolean isAvailable);
    DoctorEntity findByIsAvailable(Boolean isAvailable);
}
