package com.hackathon.ehealthcareproject.controller;

import com.hackathon.ehealthcareproject.dto.AuthResponseDto;
import com.hackathon.ehealthcareproject.dto.RegisterResponseDto;
import com.hackathon.ehealthcareproject.dto.users.LogoutResponseDto;
import com.hackathon.ehealthcareproject.dto.users.UserLoginRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserRegisterRequestDto;
import com.hackathon.ehealthcareproject.securityConfig.LogoutService;
import com.hackathon.ehealthcareproject.service.auth.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthServiceImpl authService;
    private LogoutService logoutService;

    @PostMapping("/register-admin")
    public ResponseEntity<RegisterResponseDto> registerAdmin(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        return new ResponseEntity<>(authService.registerAdmin(userRegisterRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        return new ResponseEntity<>(authService.registerUser(userRegisterRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login-admin")
    public ResponseEntity<AuthResponseDto> loginAdmin(@RequestBody UserLoginRequestDto userLoginRequestDto){
        return new ResponseEntity<>(authService.loginAdmin(userLoginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto){
        return new ResponseEntity<>(authService.loginUser(userLoginRequestDto), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   Authentication authentication){
        logoutService.logout(request, response, authentication);
        LogoutResponseDto logoutResponseDto = LogoutResponseDto.builder().response("Logout successful").build();
        return ResponseEntity.ok(logoutResponseDto);
    }

}
