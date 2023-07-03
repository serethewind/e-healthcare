package com.hackathon.ehealthcareproject.service.user;


import com.hackathon.ehealthcareproject.dto.users.UserRegisterRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserResponseDto;

import java.util.List;

public interface UsersServiceInterface {

    UserResponseDto createUser (UserRegisterRequestDto userRegisterRequestDto);

    UserResponseDto viewSingleUser(Long id);

    List<UserResponseDto> viewAllUsers();

    UserResponseDto updateUserInformation(Long id, UserRegisterRequestDto userRegisterRequestDto);

    String deleteUser(Long id);
}
