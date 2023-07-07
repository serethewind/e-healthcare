package com.hackathon.ehealthcareproject.service.staff;


import com.hackathon.ehealthcareproject.dto.staff.StaffDto;
import com.hackathon.ehealthcareproject.dto.staff.StaffResponse;
import com.hackathon.ehealthcareproject.entity.StaffEntity;
import com.hackathon.ehealthcareproject.entity.StaffType;

import java.util.List;

public interface StaffService {
    StaffResponse createProfile(StaffDto staffDto);
    List<StaffEntity> viewAllStaff();
    List<StaffResponse> viewAllDoctorsByGender(String gender);

    List<StaffResponse> viewAllStaffByGender(String gender);

//    List<StaffResponse> viewAllStaffBySpecialization(StaffType staffType);
    StaffResponse viewSingleStaff(Long id);
    List<StaffResponse> viewAllAvailableStaff(Boolean isAvailable);
    StaffResponse updateProfile(StaffDto staffDto);
    void deleteProfile(Boolean isAvailable);
}
