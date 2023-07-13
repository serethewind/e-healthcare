package com.hackathon.ehealthcareproject.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserPasswordResponseDto {
    private String username;
    private String response;
}
