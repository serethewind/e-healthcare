package com.hackathon.ehealthcareproject.controller;


import com.hackathon.ehealthcareproject.dto.users.UserResponseDto;
import com.hackathon.ehealthcareproject.dto.users.UserUpdateRequestDto;
import com.hackathon.ehealthcareproject.service.user.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health/v1/users")
@AllArgsConstructor
public class UsersController {

    private UsersServiceImpl usersService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        return ResponseEntity.ok(usersService.viewAllUsers());
    }

    @GetMapping("/home")
    public String homepage(){
        return "Welcome to the homepage. for login and register route, click the button below";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getSingleUser(@PathVariable("id") Long userId){
        return ResponseEntity.ok(usersService.viewSingleUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserDetails(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return ResponseEntity.ok(usersService.updateUserInformation(id, userUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        return ResponseEntity.ok(usersService.deleteUser(userId));
    }
}
