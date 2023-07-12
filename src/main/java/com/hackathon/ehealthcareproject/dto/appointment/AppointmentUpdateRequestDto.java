package com.hackathon.ehealthcareproject.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentUpdateRequestDto {
    private String username;
    private LocalDate localDate;
    private String doctorFirstName;
    private String message;
}
