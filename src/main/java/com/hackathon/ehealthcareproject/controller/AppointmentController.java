package com.hackathon.ehealthcareproject.controller;


import com.hackathon.ehealthcareproject.dto.appointment.AppointmentRequestDto;
import com.hackathon.ehealthcareproject.dto.appointment.AppointmentResponseDto;
import com.hackathon.ehealthcareproject.service.appointment.AppointmentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health/v1/appointment")
@AllArgsConstructor
public class AppointmentController {

    private AppointmentServiceInterface appointmentServiceInterface;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){
        return new ResponseEntity<>(appointmentServiceInterface.createAppointment(appointmentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> fetchAllAppointments(){
        return ResponseEntity.ok(appointmentServiceInterface.viewAllAppointments());
    }

    @GetMapping("{id}")
    public ResponseEntity<AppointmentResponseDto> fetchSingleAppointmentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(appointmentServiceInterface.viewSingleAppointment(id));
    }

    @GetMapping("user")
    public ResponseEntity<List<AppointmentResponseDto>> fetchAppointmentByUser(@RequestParam("user") String username){
        return ResponseEntity.ok(appointmentServiceInterface.findAppointmentByUser(username));
    }
}
