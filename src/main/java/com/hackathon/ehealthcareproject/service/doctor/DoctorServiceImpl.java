package com.hackathon.ehealthcareproject.service.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorRequestDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.exceptions.ResourceNotFoundException;
import com.hackathon.ehealthcareproject.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        doctorEntity.setFirstName(doctorRequestDto.getFirstName());
        doctorEntity.setLastName(doctorRequestDto.getLastName());
        doctorEntity.setEmail(doctorRequestDto.getEmail());
        doctorEntity.setGender(doctorRequestDto.getGender());
        doctorEntity.setSpecialization(doctorRequestDto.getSpecialization());
        doctorEntity.setPhoneNumber(doctorRequestDto.getPhoneNumber());
        doctorRepository.save(doctorEntity);
        return DoctorResponseDto.builder()
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .specialization(doctorEntity.getSpecialization())
                .gender(doctorEntity.getGender())
                .build();
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
        return doctorRepository.findAll().stream().map(doctorEntity -> DoctorResponseDto.builder()
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .specialization(doctorEntity.getSpecialization())
                .gender(doctorEntity.getGender())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponseDto> viewAllAvailableDoctors() {
        return doctorRepository.findAll().stream().filter(DoctorEntity::isAvailable).map(doctorEntity -> DoctorResponseDto.builder()
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .specialization(doctorEntity.getSpecialization())
                .gender(doctorEntity.getGender())
                .build()).collect(Collectors.toList());
    }

    @Override
    public String deleteDoctor(Long id) {
        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        doctorEntity.setAvailable(false);
        doctorRepository.save(doctorEntity);
        return "Doctor Entity deleted successfully";

    }
}
