package com.hackathon.ehealthcareproject.service.appointment;

import com.hackathon.ehealthcareproject.dto.appointment.AppointmentRequestDto;
import com.hackathon.ehealthcareproject.dto.appointment.AppointmentResponseDto;
import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import com.hackathon.ehealthcareproject.repository.AppointmentRepository;
import com.hackathon.ehealthcareproject.repository.DoctorRepository;
import com.hackathon.ehealthcareproject.repository.UserRepository;
import com.hackathon.ehealthcareproject.service.user.UsersServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentServiceInterface{
    private UserRepository userRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;


    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentRequestDto) {
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentDate(appointmentRequestDto.getLocalDate())
                .userEntity(userRepository.findUserByUsername(appointmentRequestDto.getUsername()).get())
                .build();

        return AppointmentResponseDto.builder()
                .DoctorName("Doctor")
                .response("Appointment successfully booked")
                .build();
    }

    @Override
    public AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto appointmentRequestDto) {
        return null;
    }

    @Override
    public List<AppointmentResponseDto> viewAllAppointments() {
        return null;
    }

    @Override
    public List<AppointmentResponseDto> findAppointmentByUser(String username) {
        return null;
    }

    @Override
    public AppointmentResponseDto viewSingleAppointment(Long id) {
        return null;
    }
}
