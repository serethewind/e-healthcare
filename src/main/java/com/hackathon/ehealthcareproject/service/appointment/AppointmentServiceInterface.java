package com.hackathon.ehealthcareproject.service.appointment;

import com.hackathon.ehealthcareproject.dto.appointment.AppointmentRequestDto;
import com.hackathon.ehealthcareproject.dto.appointment.AppointmentResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AppointmentServiceInterface {

    AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentRequestDto);

    AppointmentResponseDto updateAppointment(AppointmentRequestDto appointmentRequestDto);

    List<AppointmentResponseDto> viewAllAppointments();

    List<AppointmentResponseDto> findAppointmentByUser(String username);

    AppointmentResponseDto viewSingleAppointment(Long id);


}
