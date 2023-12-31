package com.hackathon.ehealthcareproject.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDto {
    private String username;
    private String doctorFirstName;
    private String doctorLastName;
    private String response;
}
