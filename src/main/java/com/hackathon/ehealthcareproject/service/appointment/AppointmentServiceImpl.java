package com.hackathon.ehealthcareproject.service.appointment;

import com.hackathon.ehealthcareproject.dto.appointment.AppointmentRequestDto;
import com.hackathon.ehealthcareproject.dto.appointment.AppointmentResponseDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.repository.AppointmentRepository;
import com.hackathon.ehealthcareproject.repository.DoctorRepository;
import com.hackathon.ehealthcareproject.repository.UserRepository;
import com.hackathon.ehealthcareproject.service.doctor.DoctorService;
import com.hackathon.ehealthcareproject.service.user.UsersServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentServiceInterface{
    private UserRepository userRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private DoctorService doctorService;
    private ModelMapper modelMapper;


    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentRequestDto) {

        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentDate(appointmentRequestDto.getLocalDate())
                .userEntity(userRepository.findUserByUsername(appointmentRequestDto.getUsername()).get())
                .doctorEntity(doctorService.randomDoctorOnSpecificDay(appointmentRequestDto.getLocalDate()))
                .build();

        DoctorEntity doctorEntity = doctorRepository.findById(appointmentEntity.getDoctorEntity().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return AppointmentResponseDto.builder()
                .doctorFirstName(doctorEntity.getFirstName())
                .doctorLastName(doctorEntity.getLastName())
                .response("Appointment successfully booked")
                .build();
    }

    @Override
    public AppointmentResponseDto updateAppointment(Long id) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        appointmentEntity.setDoctorEntity(doctorService.randomDoctorOnSpecificDay(appointmentEntity.getAppointmentDate()));
        appointmentRepository.save(appointmentEntity);
        return modelMapper.map(appointmentEntity, AppointmentResponseDto.class);
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
