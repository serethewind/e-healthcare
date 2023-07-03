package com.hackathon.ehealthcareproject.service.auth;


import com.hackathon.ehealthcareproject.dto.AuthResponseDto;
import com.hackathon.ehealthcareproject.dto.users.UserLoginRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserRegisterRequestDto;

public interface AuthServiceInterface {

    String registerUser(UserRegisterRequestDto userRegisterRequestDto);

    AuthResponseDto loginUser(UserLoginRequestDto userLoginRequestDto);
}
