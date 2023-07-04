package com.hackathon.ehealthcareproject.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequestDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
