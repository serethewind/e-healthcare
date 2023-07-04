package com.hackathon.ehealthcareproject.service.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorRequestDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    @Override
    public DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto) {
        return null;
    }

    @Override
    public DoctorResponseDto updateDoctor(DoctorRequestDto doctorRequestDto) {
        return null;
    }

    @Override
    public DoctorResponseDto viewSingleDoctor(Long id) {
        return null;
    }

    @Override
    public List<DoctorResponseDto> viewAllDoctors() {
        return null;
    }

    @Override
    public List<DoctorResponseDto> viewAllAvailableDoctors() {
        return null;
    }
}
