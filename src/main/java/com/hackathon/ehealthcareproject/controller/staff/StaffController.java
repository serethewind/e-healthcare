package com.hackathon.ehealthcareproject.controller.staff;

import com.hackathon.ehealthcareproject.dto.staff.StaffDto;
import com.hackathon.ehealthcareproject.dto.staff.StaffResponse;
import com.hackathon.ehealthcareproject.entity.StaffEntity;
import com.hackathon.ehealthcareproject.service.staff.StaffService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffController {
    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    StaffResponse createProfile(StaffDto staffDto){
        return service.createProfile(staffDto);
    }
    List<StaffEntity> viewAllStaff(){
        return service.viewAllStaff();
    }
    List<StaffResponse> viewAllStaffByGender(String gender){
        return service.viewAllDoctorsByGender(gender);
    }
    //    List<DoctorResponse> viewAllStaffBySpecialization();
    StaffResponse viewSingleStaff(Long id){
        return service.viewSingleStaff(id);
    }
    List<StaffResponse> viewAllAvailableStaff(Boolean isAvailable){
        return service.viewAllAvailableStaff(isAvailable);
    }
    StaffResponse updateProfile(StaffDto staffDto){
        return service.updateProfile(staffDto);
    }
    void deleteProfile(Boolean isAvailable){
        service.deleteProfile(isAvailable);
    }
}
