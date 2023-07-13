package com.hackathon.ehealthcareproject.controller;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorRequestDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.service.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/health/doctor")
@AllArgsConstructor
public class DoctorController {
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        return new ResponseEntity<>(doctorService.createDoctor(doctorRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> fetchAllDoctors(){
        return new ResponseEntity<>(doctorService.viewAllDoctors(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DoctorResponseDto> fetchSingleDoctor(@PathVariable("id") long id){
        return new ResponseEntity<>(doctorService.viewSingleDoctor(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<DoctorResponseDto> updateSingleDoctor(@PathVariable("id") long id, @RequestBody DoctorRequestDto doctorRequestDto){
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctorRequestDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSingleDoctor(@PathVariable("id") long id){
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }

    @GetMapping("available")
    public ResponseEntity<List<DoctorResponseDto>> fetchAvailableDoctors(){
        return ResponseEntity.ok(doctorService.viewAllAvailableDoctors());
    }
}
