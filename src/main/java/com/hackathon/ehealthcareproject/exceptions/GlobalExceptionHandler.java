package com.hackathon.ehealthcareproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsClass> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {

        ErrorDetailsClass errorDetailsClass = ErrorDetailsClass.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .path(webRequest.getDescription(false))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errorDetailsClass, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetailsClass> handlerBadRequestException(BadRequestException e, WebRequest webRequest) {

        ErrorDetailsClass errorDetailsClass = ErrorDetailsClass.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(errorDetailsClass, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsClass> handlerGlobalException(Exception e, WebRequest webRequest) {

        ErrorDetailsClass errorDetailsClass = ErrorDetailsClass.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return new ResponseEntity<>(errorDetailsClass, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
