package com.hackathon.ehealthcareproject.controller;


import com.hackathon.ehealthcareproject.dto.users.UserResponseDto;
import com.hackathon.ehealthcareproject.dto.users.UserUpdateRequestDto;
import com.hackathon.ehealthcareproject.service.user.UsersServiceImpl;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health/v1/users")
@AllArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                title = "Skincare e-health solutions",
                description = "SkinLikeMilk REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Noah",
                        email = "osasereu@gmail.com",
                        url = "https://github.com/serethewind"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/serethewind"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Skin care Rest API Documentation",
                url = "https://github.com/serethewind"
        )
)
@Tag(
        name = "User Account Service REST APIs/Endpoint",
        description = "Endpoints for manipulating User account"
)
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

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getSingleUser(@PathVariable("userId") Long userId){
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
