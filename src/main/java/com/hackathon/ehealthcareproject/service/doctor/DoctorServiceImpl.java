package com.hackathon.ehealthcareproject.service.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorRequestDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Override
    public DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto) {
        DoctorEntity createdDoctor = DoctorEntity.builder()
                .firstName(doctorRequestDto.getFirstName())
                .lastName(doctorRequestDto.getLastName())
                .email(doctorRequestDto.getEmail())
                .gender(doctorRequestDto.getGender())
                .specialization(doctorRequestDto.getSpecialization())
                .phoneNumber(doctorRequestDto.getPhoneNumber())
                .build();

        doctorRepository.save(createdDoctor);
        return DoctorResponseDto.builder()
                .firstName(createdDoctor.getFirstName())
                .lastName(createdDoctor.getLastName())
                .gender(createdDoctor.getGender())
                .specialization(createdDoctor.getSpecialization())
                .build();
    }

    @Override
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto doctorRequestDto) {
        return null;
    }

    @Override
    public DoctorResponseDto viewSingleDoctor(Long id) {
        DoctorEntity foundDoctor = doctorRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return DoctorResponseDto.builder()
                .firstName(foundDoctor.getFirstName())
                .lastName(foundDoctor.getLastName())
                .gender(foundDoctor.getGender())
                .specialization(foundDoctor.getSpecialization())
                .build();
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
