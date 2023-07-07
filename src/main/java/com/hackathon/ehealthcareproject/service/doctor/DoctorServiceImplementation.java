package com.hackathon.ehealthcareproject.service.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.Data;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponse;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.repository.doctorRepository.DoctorRepository;
import com.hackathon.ehealthcareproject.utils.doctor.Utils;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DoctorServiceImplementation implements DoctorService{

    private final DoctorRepository doctorRepository;
    private List<DoctorEntity> view;

    public DoctorServiceImplementation(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorResponse createProfile(DoctorDto doctor) {

        if (doctorRepository.existsByEmail(doctor.getEmail())){
            return DoctorResponse.builder()
                    .responseCode(Utils.EXISTED_RESPONSE_CODE)
                    .responseMessage(Utils.EXISTED_RESPONSE_MESSAGE)
                    .data(null)
                     .build();
        }
        DoctorEntity doc = DoctorEntity.builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())
                .gender(doctor.getGender())
                .specialization(doctor.getSpecialization())
                .phoneNumber(doctor.getPhoneNumber())
                .isAvailable(doctor.getIsAvailable())
                .dateOfBirth(doctor.getDateOfBirth())
                .build();
        DoctorEntity doctorCreated = doctorRepository.save(doc);

        return DoctorResponse.builder()
                .responseCode(Utils.REGISTERED_RESPONSE_CODE)
                .responseMessage(Utils.REGISTERED_RESPONSE_MESSAGE)
                .data(Data.builder()
                        .name(doctorCreated.getFirstName() + " " + doctorCreated.getLastName()+ " dr.")
                        .build())
                .build();
    }

    @Override
    public List<DoctorEntity> viewAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorResponse viewSingleDoctor(Long id) {

        if (!doctorRepository.existsById(id)){
            return DoctorResponse.builder()
                    .responseCode(Utils.DOCTOR_NOT_EXISTS_RESPONSE_CODE)
                    .responseMessage(Utils.DOCTOR_NOT_EXISTS_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        DoctorEntity view = doctorRepository.findById(id).get();

        return DoctorResponse.builder()
                .responseCode(Utils.FOUND_RESPONSE_CODE)
                .responseMessage(Utils.FOUND_RESPONSE_MESSAGE)
                .data(Data.builder()
                        .name(view.getFirstName()+ " " + view.getLastName())
                        .build())
                .build();
    }

    @Override
    public List<DoctorResponse> viewAllAvailableDoctors(Boolean isAvailable) {
            List<DoctorEntity> doctorEntityList = doctorRepository.findAll();
            List<DoctorEntity> foundedDoctors = doctorEntityList.stream().filter(doctorEntity -> isAvailable.equals(doctorEntity.getIsAvailable())).toList();
            return foundedDoctors.stream().map(eachDoctor -> DoctorResponse.builder()
                    .responseCode(Utils.AVAILABLE_RESPONSE_CODE)
                    .responseMessage(Utils.AVAILABLE_RESPONSE_MESSAGE)
                    .data(Data.builder()
                            .name(eachDoctor.getFirstName() + " " + eachDoctor.getLastName())
                            .build())
                    .build()).toList();
    }

    @Override
    public List<DoctorResponse> viewAllDoctorsByGender(String gender) {
        List<DoctorEntity> doctorEntityList = doctorRepository.findAll();
        List<DoctorEntity> foundDoctors = doctorEntityList.stream().filter((doctor) -> gender.equalsIgnoreCase(doctor.getGender())).toList();
        return foundDoctors.stream().map((eachDoctor) -> DoctorResponse.builder()
                .responseCode(Utils.AVAILABLE_RESPONSE_CODE)
                .responseMessage(Utils.AVAILABLE_RESPONSE_MESSAGE)
                .data(Data.builder()
                        .name(eachDoctor.getFirstName()+ " "+ eachDoctor.getLastName())
                        .build())
                .build()).collect(Collectors.toList());
    }

    @Override
    public DoctorResponse updateProfile(DoctorDto doctorDto) {
        if (!doctorRepository.existsByEmail(doctorDto.getEmail())){
            return DoctorResponse.builder()
                    .responseCode(Utils.NOT_FOUND_RESPONSE_CODE)
                    .responseMessage(Utils.NOT_FOUND_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        DoctorEntity doc = DoctorEntity.builder()
                .firstName(doctorDto.getFirstName())
                .lastName(doctorDto.getLastName())
                .email(doctorDto.getEmail())
                .gender(doctorDto.getGender())
                .specialization(doctorDto.getSpecialization())
                .phoneNumber(doctorDto.getPhoneNumber())
                .dateOfBirth(doctorDto.getDateOfBirth())
                .build();
        DoctorEntity doctorCreated = doctorRepository.save(doc);

        return DoctorResponse.builder()
                .responseCode(Utils.PROFILE_UPDATED_RESPONSE_CODE)
                .responseMessage(Utils.PROFILE_UPDATED_RESPONSE_MESSAGE)
                .data(Data.builder()
                        .name(doctorCreated.getFirstName() + " " + doctorCreated.getLastName()+ " dr.")
                        .build())
                .build();
    }

    @Override
    public void deleteProfile(Long id) {
        if (!doctorRepository.existsById(id)){
            DoctorResponse.builder()
                    .responseCode(Utils.NOT_FOUND_RESPONSE_CODE)
                    .responseMessage(Utils.NOT_FOUND_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        else {
            if (doctorRepository.existsById(id)){
                DoctorEntity deleted = doctorRepository.findById(id).get();
                deleted.setIsAvailable(false);
                doctorRepository.save(deleted);
            }
                System.out.println("Personnel successfully deleted");
        }
    }

    @Override
    public DoctorResponse reactivation(Long id) {
//        if (doctorRepository.existsByIsAvailable(true)){
//            DoctorResponse.builder()
//                    .responseCode(Utils.FOUND_RESPONSE_CODE)
//                    .responseMessage(Utils.FOUND_RESPONSE_MESSAGE)
//                    .data(Data.builder()
//                            .name("Found")
//                            .build())
//                    .build();
//        }
//        if (doctorRepository.existsByIsAvailable(false)){
//            DoctorEntity reactivate = doctorRepository.findByIsAvailable(false);
//            reactivate.setIsAvailable(true);
//            doctorRepository.save(reactivate);
//        }
//        return DoctorResponse.builder()
//                .responseCode(Utils.REACTIVATION_RESPONSE_CODE)
//                .responseMessage(Utils.REACTIVATION_RESPONSE_MESSAGE)
//            .build();

        //find by id
        //check if this doctor entity is available
        //make

        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        while (doctorEntity.getIsAvailable().equals(false)) {
            doctorEntity.setIsAvailable(true);
        }

        DoctorEntity active = doctorRepository.save(doctorEntity);
        return DoctorResponse.builder()
                .responseCode(Utils.REACTIVATION_RESPONSE_CODE)
                .responseMessage(Utils.REACTIVATION_RESPONSE_MESSAGE)
                .data(Data.builder()
                        .name(active.getFirstName()+ " " + active.getLastName())
                        .build())
                .build();

    }




}
