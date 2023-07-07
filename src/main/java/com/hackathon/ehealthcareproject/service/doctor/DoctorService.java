package com.hackathon.ehealthcareproject.service.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponse;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;

import java.util.List;

public interface DoctorService {
    DoctorResponse createProfile(DoctorDto doctor);
    List<DoctorEntity> viewAllDoctors();
    List<DoctorResponse> viewAllDoctorsByGender(String gender);
//    List<DoctorResponse> viewAllDoctorsBySpecialization();
    DoctorResponse viewSingleDoctor(Long id);
    List<DoctorResponse> viewAllAvailableDoctors(Boolean isAvailable);
    DoctorResponse updateProfile(DoctorDto doctorDto);
    void deleteProfile(Long id);
}
