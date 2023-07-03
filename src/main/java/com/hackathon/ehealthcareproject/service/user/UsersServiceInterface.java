package com.hackathon.ehealthcareproject.service.user;


import com.hackathon.ehealthcareproject.dto.users.UserRegisterRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserResponseDto;
import com.hackathon.ehealthcareproject.dto.users.UserUpdateRequestDto;

import java.util.List;

public interface UsersServiceInterface {

    UserResponseDto viewSingleUser(Long id);

    List<UserResponseDto> viewAllUsers();

    UserResponseDto updateUserInformation(Long id, UserUpdateRequestDto userUpdateRequestDto);

    String deleteUser(Long id);
}
