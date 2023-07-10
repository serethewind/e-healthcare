package com.hackathon.ehealthcareproject.service.appointment;

import com.hackathon.ehealthcareproject.dto.appointment.AppointmentRequestDto;
import com.hackathon.ehealthcareproject.dto.appointment.AppointmentResponseDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponseDto;
import com.hackathon.ehealthcareproject.dto.email.EmailDetails;
import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.entity.UserEntity;
import com.hackathon.ehealthcareproject.repository.AppointmentRepository;
import com.hackathon.ehealthcareproject.repository.DoctorRepository;
import com.hackathon.ehealthcareproject.repository.UserRepository;
import com.hackathon.ehealthcareproject.service.doctor.DoctorService;
import com.hackathon.ehealthcareproject.service.emails.EmailServiceInterface;
import com.hackathon.ehealthcareproject.service.user.UsersServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentServiceInterface{
    private UserRepository userRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private DoctorService doctorService;
    private ModelMapper modelMapper;
    private EmailServiceInterface emailServiceInterface;


    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentRequestDto) {

        UserEntity user = userRepository.findUserByUsername(appointmentRequestDto.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentDate(appointmentRequestDto.getLocalDate())
                .userEntity(user)
                .comments(appointmentRequestDto.getMessage())
                .remarks("Appointment successfully booked")
                .doctorEntity(doctorService.randomDoctorOnSpecificDay(appointmentRequestDto.getLocalDate()))
                .build();

        appointmentRepository.save(appointmentEntity);
        DoctorEntity doctorEntity = doctorRepository.findById(appointmentEntity.getDoctorEntity().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("Appointment Booked Successfully")
                .messageBody("Hi " + user.getUsername() + ", Our doctors and specialists are here to care for you 24/7.\n" +
                        "\n" +
                        "           Our amiable  Dr. " + doctorEntity.getFirstName() + " " + doctorEntity.getLastName() + " will be at your service on your preferred date " + appointmentRequestDto.getLocalDate() + "\n" +
                        "           SkinLikeMilk is packed with healthy features, waiting to support you and your health. We are so glad to have you here.  ")
                .build();

        emailServiceInterface.sendSimpleMessage(emailDetails);



        return AppointmentResponseDto.builder()
                .username(appointmentEntity.getUserEntity().getUsername())
                .doctorFirstName(doctorEntity.getFirstName())
                .doctorLastName(doctorEntity.getLastName())
                .response(appointmentEntity.getRemarks())
                .build();
    }

    @Override
    public AppointmentResponseDto updateAppointment(Long id) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        DoctorEntity doctorEntity = appointmentEntity.getDoctorEntity();
        appointmentEntity.setDoctorEntity(doctorService.randomizingRandomDoctor(doctorEntity, appointmentEntity.getAppointmentDate()));
        appointmentRepository.save(appointmentEntity);
        return modelMapper.map(appointmentEntity, AppointmentResponseDto.class);
    }

    @Override
    public List<AppointmentResponseDto> viewAllAppointments() {
       return appointmentRepository.findAll().stream().map(appointmentEntity -> AppointmentResponseDto.builder()
               .username(appointmentEntity.getUserEntity().getUsername())
               .doctorFirstName(appointmentEntity.getDoctorEntity().getFirstName())
               .doctorLastName(appointmentEntity.getDoctorEntity().getLastName())
               .response(appointmentEntity.getRemarks())
               .build()).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDto> findAppointmentByUser(String username) {
        return appointmentRepository.findByUserEntity_Username(username).stream().map(appointmentEntity -> AppointmentResponseDto.builder()
                .username(appointmentEntity.getUserEntity().getUsername())
                .doctorFirstName(appointmentEntity.getDoctorEntity().getFirstName())
                .doctorLastName(appointmentEntity.getDoctorEntity().getLastName())
                .response(appointmentEntity.getRemarks())
                .build()).collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDto viewSingleAppointment(Long id) {
        return appointmentRepository.findById(id).map(appointmentEntity -> AppointmentResponseDto.builder()
                .username(appointmentEntity.getUserEntity().getUsername())
                .doctorFirstName(appointmentEntity.getDoctorEntity().getFirstName())
                .doctorLastName(appointmentEntity.getDoctorEntity().getLastName())
                .response(appointmentEntity.getRemarks())
                .build()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public String markAppointmentAsFulfilled(Long id) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        appointmentEntity.setFulfilled(true);
        appointmentRepository.save(appointmentEntity);
        return "Appointment fulfilled";
    }
}
