package com.hackathon.ehealthcareproject.controller.doctor;

import com.hackathon.ehealthcareproject.dto.doctor.DoctorDto;
import com.hackathon.ehealthcareproject.dto.doctor.DoctorResponse;
import com.hackathon.ehealthcareproject.entity.DoctorEntity;
import com.hackathon.ehealthcareproject.service.doctor.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @PostMapping("/doctors")
    public DoctorResponse createProfile(@RequestBody DoctorDto doctor){
        return doctorService.createProfile(doctor);
    }
    @GetMapping("/allDoctors")
    public List<DoctorEntity> viewAllDoctors(){
        return doctorService.viewAllDoctors();
    }
    @GetMapping("/{singleDoctor}")
    public DoctorResponse viewSingleDoctor(@PathVariable("singleDoctors") Long id){
        return doctorService.viewSingleDoctor(id);
    }
    @GetMapping("/{AllDoctors}")
    public List<DoctorResponse> viewAllAvailableDoctors(@PathVariable("AllDoctors") Boolean isAvailable){
        return doctorService.viewAllAvailableDoctors(isAvailable);
    }
    @GetMapping("/{DoctorsByGender}")
    public List<DoctorResponse> viewAllDoctorsByGender(@PathVariable("DoctorsByGender") String gender){
        return doctorService.viewAllDoctorsByGender(gender);
    }
    @PutMapping
    public DoctorResponse updateProfile(@RequestBody DoctorDto doctorDto){
        return doctorService.updateProfile(doctorDto);
    }
    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable("id") Long id){
        doctorService.deleteProfile(id);
    }
}
