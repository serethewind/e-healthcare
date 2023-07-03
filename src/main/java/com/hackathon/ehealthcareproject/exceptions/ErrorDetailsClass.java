package com.hackathon.ehealthcareproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailsClass {

    private LocalDateTime timestamp;
    private String message;
    private String path;
    private Integer statusCode;

}
