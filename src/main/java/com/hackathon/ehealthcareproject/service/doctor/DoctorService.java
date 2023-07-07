package com.hackathon.ehealthcareproject.service.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorRequestDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;

import java.time.LocalDate;
import java.util.List;

public interface DoctorService {
    DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto);

    DoctorResponseDto updateDoctor(Long id, DoctorRequestDto doctorRequestDto);

    DoctorResponseDto viewSingleDoctor(Long id);

    List<DoctorResponseDto> viewAllDoctors();

    List<DoctorResponseDto> viewAllAvailableDoctors();

    String deleteDoctor(Long id);

    DoctorEntity randomDoctorOnSpecificDay(LocalDate localDate);
}
