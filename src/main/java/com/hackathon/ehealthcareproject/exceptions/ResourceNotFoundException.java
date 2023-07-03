package com.hackathon.ehealthcareproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1;

    public ResourceNotFoundException(String message){
        super(message);
    }

//    private String resourceName;
//    private String fieldName;
//    private Long fieldValue;

//    public ResourceNotFoundException(String message, String resourceName, String fieldName, Long fieldValue) {
//        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }
}
