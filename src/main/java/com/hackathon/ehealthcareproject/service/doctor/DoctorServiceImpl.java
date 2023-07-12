package com.hackathon.ehealthcareproject.service.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorRequestDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.entity.DaysOfWeek;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.exceptions.ResourceNotFoundException;
import com.hackathon.ehealthcareproject.repository.DoctorRepository;
import com.hackathon.ehealthcareproject.utils.AppointmentLogic;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
                .imagesUri(doctorRequestDto.getImageUris())
                .about(doctorRequestDto.getAbout())
                .gender(doctorRequestDto.getGender())
                .specialization(doctorRequestDto.getSpecialization())
                .phoneNumber(doctorRequestDto.getPhoneNumber())
                .availableDays(AppointmentLogic.assignDays())
                .build();

        doctorRepository.save(createdDoctor);
        return DoctorResponseDto.builder()
                .firstName(createdDoctor.getFirstName())
                .lastName(createdDoctor.getLastName())
                .about(createdDoctor.getAbout())
                .imageUris(createdDoctor.getImagesUri())
                .gender(createdDoctor.getGender())
                .specialization(createdDoctor.getSpecialization())
                .build();
    }

    @Override
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto doctorRequestDto) {
        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        doctorEntity.setFirstName(doctorRequestDto.getFirstName());
        doctorEntity.setLastName(doctorRequestDto.getLastName());
        doctorEntity.setAbout(doctorRequestDto.getAbout());
        doctorEntity.setImagesUri(doctorRequestDto.getImageUris());
        doctorEntity.setEmail(doctorRequestDto.getEmail());
        doctorEntity.setGender(doctorRequestDto.getGender());
        doctorEntity.setSpecialization(doctorRequestDto.getSpecialization());
        doctorEntity.setPhoneNumber(doctorRequestDto.getPhoneNumber());
        doctorRepository.save(doctorEntity);

        return DoctorResponseDto.builder()
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .imageUris(doctorEntity.getImagesUri())
                .about(doctorEntity.getAbout())
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
                .about(foundDoctor.getAbout())
                .imageUris(foundDoctor.getImagesUri())
                .gender(foundDoctor.getGender())
                .specialization(foundDoctor.getSpecialization())
                .build();
    }

    @Override
    public List<DoctorResponseDto> viewAllDoctors() {
        return doctorRepository.findAll().stream().map(doctorEntity -> DoctorResponseDto.builder()
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .about(doctorEntity.getAbout())
                .imageUris(doctorEntity.getImagesUri())
                .specialization(doctorEntity.getSpecialization())
                .gender(doctorEntity.getGender())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponseDto> viewAllAvailableDoctors() {
        return doctorRepository.findAll().stream().filter(DoctorEntity::isAvailable).map(doctorEntity -> DoctorResponseDto.builder()
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .about(doctorEntity.getAbout())
                .imageUris(doctorEntity.getImagesUri())
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

    public DoctorEntity randomDoctorOnSpecificDay(LocalDate localDate){

      List<DoctorEntity> doctorEntities = new ArrayList<>(doctorRepository.findAll().stream()
              .filter(doctorEntity -> doctorEntity.getAvailableDays()
                      .contains(localDate.getDayOfWeek().toString())).toList());
      Collections.shuffle(doctorEntities);
      return doctorEntities.iterator().next();
    }

    public DoctorEntity randomizingRandomDoctor(DoctorEntity previousDoctor, LocalDate localDate){

        List<DoctorEntity> doctorEntities = doctorRepository.findAll().stream().filter(doctorEntity -> doctorEntity.getAvailableDays().contains(localDate.getDayOfWeek().toString())).toList();
        List<DoctorEntity> newDoctorEntities = new ArrayList<>(doctorEntities.stream().filter(doctorEntity -> !doctorEntity.equals(previousDoctor)).toList());
        Collections.shuffle(newDoctorEntities);
        return doctorEntities.iterator().next();
    }

}
