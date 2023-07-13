package com.hackathon.ehealthcareproject.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto {
    private String firstName;
    private String otherName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private String address;
    private String nextOfKin;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
