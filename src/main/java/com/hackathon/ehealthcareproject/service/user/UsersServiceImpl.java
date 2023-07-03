package com.hackathon.ehealthcareproject.service.user;


import com.hackathon.ehealthcareproject.dto.users.UserRegisterRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserResponseDto;
import com.hackathon.ehealthcareproject.entity.UserEntity;
import com.hackathon.ehealthcareproject.exceptions.BadRequestException;
import com.hackathon.ehealthcareproject.exceptions.ResourceNotFoundException;
import com.hackathon.ehealthcareproject.repository.UserRepository;
import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersServiceInterface {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto) {
        UserEntity user = modelMapper.map(userRegisterRequestDto, UserEntity.class);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto viewSingleUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> viewAllUsers() {
        List<UserEntity> allUsers = userRepository.findAll();
        return allUsers.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUserInformation(Long id, UserRegisterRequestDto userRegisterRequestDto) {
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found");
        }

        UserEntity foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        modelMapper.map(userRegisterRequestDto, foundUser);
        userRepository.save(foundUser);
        return modelMapper.map(foundUser, UserResponseDto.class);
    }

    @Override
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new BadRequestException("User not found. Delete option failed");
        }

        userRepository.deleteById(id);
        return "User with " + id + " successfully deleted.";
    }
}
