package com.hackathon.ehealthcareproject.service.auth;


import com.hackathon.ehealthcareproject.dto.AuthResponseDto;
import com.hackathon.ehealthcareproject.dto.RegisterResponseDto;
import com.hackathon.ehealthcareproject.dto.users.UserLoginRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserPasswordResetRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserPasswordResponseDto;
import com.hackathon.ehealthcareproject.dto.users.UserRegisterRequestDto;

public interface AuthServiceInterface {

    RegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);

    RegisterResponseDto registerAdmin(UserRegisterRequestDto userRegisterRequestDto);

    AuthResponseDto loginUser(UserLoginRequestDto userLoginRequestDto);

    AuthResponseDto loginAdmin(UserLoginRequestDto userLoginRequestDto);

    UserPasswordResponseDto resetPassword(UserPasswordResetRequestDto userPasswordResetRequestDto);
}
