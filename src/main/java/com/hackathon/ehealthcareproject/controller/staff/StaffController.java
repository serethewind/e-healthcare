package com.hackathon.ehealthcareproject.controller.staff;

import com.hackathon.ehealthcareproject.dto.staff.StaffDto;
import com.hackathon.ehealthcareproject.dto.staff.StaffResponse;
import com.hackathon.ehealthcareproject.entity.StaffEntity;
import com.hackathon.ehealthcareproject.entity.StaffType;
import com.hackathon.ehealthcareproject.service.staff.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffController {
    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }
    @PostMapping("/staff")
    StaffResponse createProfile(@RequestBody StaffDto staffDto){
        return service.createProfile(staffDto);
    }
    @GetMapping("/allStaff")
    List<StaffEntity> viewAllStaff(){
        return service.viewAllStaff();
    }
    @GetMapping("/{staffByGender}")
    List<StaffResponse> viewAllStaffByGender(@PathVariable("staffByGender") String gender){
        return service.viewAllStaffByGender(gender);
    }
    @GetMapping("/{singleStaff}")
    StaffResponse viewSingleStaff(@PathVariable("singleStaff") Long id){
        return service.viewSingleStaff(id);
    }
    @GetMapping("/{allStaffAvailable}")
    List<StaffResponse> viewAllAvailableStaff(@PathVariable("allStaffAvailable") Boolean isAvailable){
        return service.viewAllAvailableStaff(isAvailable);
    }
    @GetMapping("/staff/specialization")
    List<StaffResponse> viewAllStaffBySpecialization(@RequestBody StaffType staffType){
        return service.viewAllStaffBySpecialization(staffType);
    }
    @PutMapping("/update/staff")
    StaffResponse updateProfile(@RequestBody StaffDto staffDto){
        return service.updateProfile(staffDto);
    }
    @DeleteMapping("/staff/id")
    void deleteProfile(@RequestBody Long id){
        service.deleteProfile(id);
    }
    @PutMapping("/reactivate/staff")
    StaffResponse reactivation (@RequestBody Long id){
        return service.reactivation(id);
    }
}
