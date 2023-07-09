package com.hackathon.ehealthcareproject.controller;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorRequestDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.service.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health/doctor")
@AllArgsConstructor
public class DoctorController {
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        return new ResponseEntity<>(doctorService.createDoctor(doctorRequestDto), HttpStatus.CREATED);
    }
}
