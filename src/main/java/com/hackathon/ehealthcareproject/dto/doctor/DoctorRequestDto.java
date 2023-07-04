package com.hackathon.ehealthcareproject.dto.doctor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String specialization;
    private String phoneNumber;
}
