package com.hackathon.ehealthcareproject.dto.staff;

import com.hackathon.ehealthcareproject.entity.StaffType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String specialization;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private Boolean isAvailable = true;
    @Enumerated(EnumType.STRING)
    private StaffType staffType;
}
