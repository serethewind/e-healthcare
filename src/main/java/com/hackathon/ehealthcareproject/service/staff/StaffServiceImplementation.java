package com.hackathon.ehealthcareproject.service.staff;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponse;
import com.hackathon.ehealthcareproject.dto.staff.Data;
import com.hackathon.ehealthcareproject.dto.staff.StaffDto;
import com.hackathon.ehealthcareproject.dto.staff.StaffResponse;
import com.hackathon.ehealthcareproject.entity.StaffEntity;
import com.hackathon.ehealthcareproject.entity.StaffType;
import com.hackathon.ehealthcareproject.repository.staffRepository.StaffRepository;
import com.hackathon.ehealthcareproject.utils.doctor.Utils;
import com.hackathon.ehealthcareproject.utils.staff.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImplementation implements StaffService{
    private final StaffRepository staffRepository;

    public StaffServiceImplementation(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffResponse createProfile(StaffDto staffDto) {
        if (staffRepository.existsByEmail(staffDto.getEmail())){
            return StaffResponse.builder()
                    .responseCode(ResponseUtils.EXISTED_RESPONSE_CODE)
                    .responseMessage(ResponseUtils.EXISTED_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        StaffEntity staff = StaffEntity.builder()
                .firstName(staffDto.getFirstName())
                .lastName(staffDto.getLastName())
                .email(staffDto.getEmail())
                .dateOfBirth(staffDto.getDateOfBirth())
                .phoneNumber(staffDto.getPhoneNumber())
                .gender(staffDto.getGender())
                .specialization(staffDto.getSpecialization())
                .build();

        StaffEntity staffInfo = staffRepository.save(staff);

        return StaffResponse.builder()
                .responseCode(ResponseUtils.REGISTERED_RESPONSE_CODE)
                .responseMessage(ResponseUtils.EXISTED_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .name(staffInfo.getFirstName()+ " " + staffInfo.getLastName())
                        .build())
                .build();
    }

    @Override
    public List<StaffEntity> viewAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public List<StaffResponse> viewAllStaffByGender(String gender) {
        List<StaffEntity> staffEntityList = staffRepository.findAll();
        List<StaffEntity> foundStaffs = staffEntityList.stream().filter((doctor) -> gender.equalsIgnoreCase(doctor.getGender())).toList();
        return foundStaffs.stream().map((eachStaff) -> StaffResponse.builder()
                .responseCode(Utils.AVAILABLE_RESPONSE_CODE)
                .responseMessage(Utils.AVAILABLE_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .name(eachStaff.getFirstName()+ " "+ eachStaff.getLastName())
                        .build())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> viewAllStaffBySpecialization(StaffType staffType) {
        List<StaffEntity> staffEntities = staffRepository.findAll();
        List<StaffEntity> staffEntityList = staffEntities.stream().filter(staffEntity -> staffType.equals(staffEntity.getStaffType())).toList();
        return staffEntityList.stream().map(eachStaff -> StaffResponse.builder()
                .responseCode(ResponseUtils.AVAILABLE_RESPONSE_CODE)
                .responseMessage(ResponseUtils.AVAILABLE_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .name(eachStaff.getFirstName()+ " "+ eachStaff.getLastName())
                        .build())
                .build()).collect(Collectors.toList());
    }


//    @Override
//    public List<StaffResponse> viewAllStaffByGender(String gender) {
//        List<StaffEntity> staffEntities = staffRepository.findAll();
//        List<StaffEntity> staffEntityList = staffEntities.stream().filter(staffEntity -> gender.equalsIgnoreCase(staffEntity.getGender())).toList();
//        return staffEntityList.stream().map(eachStaff -> StaffResponse.builder()
//                .responseCode(ResponseUtils.AVAILABLE_RESPONSE_CODE)
//                .responseMessage(ResponseUtils.AVAILABLE_RESPONSE_MESSAGE)
//                .data((lombok.Data) Data.builder()
//                        .name(eachStaff.getFirstName()+" "+ eachStaff.getLastName())
//                        .build())
//                .build()).collect(Collectors.toList());
//    }

//    @Override
//    public List<StaffResponse> viewAllStaffByGender(String gender) {
//
//    }

//    @Override
//    public List<StaffResponse> viewAllStaffBySpecialization(StaffType staffType) {
//        List<StaffEntity> staffEntities = staffRepository.findAll();
//        List<StaffEntity> staffEntityList = staffEntities.stream().filter(staffEntity -> staffType.equals(staffEntity.getStaffType())).toList();
//        return staffEntityList.stream().map(eachStaff -> StaffResponse.builder()
//                .responseCode(ResponseUtils.AVAILABLE_RESPONSE_CODE)
//                .responseMessage(ResponseUtils.AVAILABLE_RESPONSE_MESSAGE)
//                .build()
//                .data());
//    }

    @Override
    public StaffResponse viewSingleStaff(Long id) {
        if (!staffRepository.existsById(id)){
            return StaffResponse.builder()
                    .responseCode(ResponseUtils.NOT_FOUND_RESPONSE_CODE)
                    .responseMessage(ResponseUtils.NOT_FOUND_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        StaffEntity view = staffRepository.findById(id).get();
        return StaffResponse.builder()
                .responseMessage(ResponseUtils.FOUND_RESPONSE_CODE)
                .responseCode(ResponseUtils.FOUND_RESPONSE_MESSAGE)
                .data((lombok.Data)Data.builder()
                        .name(view.getFirstName()+ " " + view.getLastName())
                        .build())
                .build();
    }

    @Override
    public List<StaffResponse> viewAllAvailableStaff(Boolean isAvailable) {
        List<StaffEntity> staffEntityList = staffRepository.findAll();
        List<StaffEntity> foundedStaff = staffEntityList.stream().filter(staffEntity -> isAvailable.equals(staffEntity.getIsAvailable())).toList();
        return foundedStaff.stream().map(eachStaff -> StaffResponse.builder()
                .responseCode(Utils.AVAILABLE_RESPONSE_CODE)
                .responseMessage(Utils.AVAILABLE_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .name(eachStaff.getFirstName() + " " + eachStaff.getLastName())
                        .build())
                .build()).toList();
    }

    @Override
    public StaffResponse updateProfile(StaffDto staffDto) {
        if (!staffRepository.existsByEmail(staffDto.getEmail())){
            return StaffResponse.builder()
                    .responseCode(ResponseUtils.NOT_FOUND_RESPONSE_CODE)
                    .responseMessage(ResponseUtils.NOT_FOUND_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        StaffEntity staff = StaffEntity.builder()
                .firstName(staffDto.getFirstName())
                .lastName(staffDto.getLastName())
                .email(staffDto.getEmail())
                .dateOfBirth(staffDto.getDateOfBirth())
                .phoneNumber(staffDto.getPhoneNumber())
                .gender(staffDto.getGender())
                .specialization(staffDto.getSpecialization())
                .build();

        StaffEntity staffInfo = staffRepository.save(staff);

        return StaffResponse.builder()
                .responseCode(ResponseUtils.REGISTERED_RESPONSE_CODE)
                .responseMessage(ResponseUtils.EXISTED_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .name(staffInfo.getFirstName()+ " " + staffInfo.getLastName())
                        .build())
                .build();
    }

    @Override
    public void deleteProfile(Long id) {
        if (!staffRepository.existsById(id)){
            DoctorResponse.builder()
                    .responseCode(ResponseUtils.NOT_FOUND_RESPONSE_CODE)
                    .responseMessage(ResponseUtils.NOT_FOUND_RESPONSE_MESSAGE)
                    .data(null)
                    .build();
        }
        else {
            if (staffRepository.existsById(id)){
                StaffEntity deleted = staffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                deleted.setIsAvailable(false);
                staffRepository.save(deleted);
            }
            System.out.println("Personnel successfully deleted");
        }
    }

    @Override
    public StaffResponse reactivation(Long id) {
        StaffEntity staffEntity = staffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        while (staffEntity.getIsAvailable().equals(false)) {
            staffEntity.setIsAvailable(true);
        }
        StaffEntity active = staffRepository.save(staffEntity);
        return StaffResponse.builder()
                .responseCode(ResponseUtils.NOT_FOUND_RESPONSE_CODE)
                .responseMessage(ResponseUtils.NOT_FOUND_RESPONSE_MESSAGE)
                .data((lombok.Data) Data.builder()
                        .name(active.getFirstName()+ " " + active.getLastName())
                        .build())
                .build();
    }
}
