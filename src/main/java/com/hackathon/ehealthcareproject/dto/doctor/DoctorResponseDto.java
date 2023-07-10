package com.hackathon.ehealthcareproject.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDto {
    private long id;
    private String firstName;
    private String lastName;
    private String about;
    private String gender;
    private String specialization;
}
