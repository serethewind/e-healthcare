package com.hackathon.ehealthcareproject.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class DoctorResponse {
    private String responseCode;
    private String responseMessage;
    private Data data;
}
