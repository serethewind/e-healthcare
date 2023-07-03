//package com.hackathon.ehealthcareproject.controller;
//
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/arkticles/v1/users")
//@AllArgsConstructor
//public class UsersController {
//
//    private UsersServiceImpl usersService;
//
//    @GetMapping
//    public ResponseEntity<List<UserResponseDto>> getUsers(){
//        return ResponseEntity.ok(usersService.viewAllUsers());
//    }
//
//    @GetMapping("/home")
//    public String homepage(){
//        return "Welcome to the homepage. for login and register route, click the button below";
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponseDto> getSingleUser(@PathVariable("id") Long userId){
//        return ResponseEntity.ok(usersService.viewSingleUser(userId));
//    }
//
//    @PostMapping
//    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
//        return ResponseEntity.ok(usersService.createUser(userRegisterRequestDto));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserResponseDto> updateUserDetails(@PathVariable("id") Long id, @RequestBody UserRegisterRequestDto userRegisterRequestDto){
//        return ResponseEntity.ok(usersService.updateUserInformation(id, userRegisterRequestDto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
//        return ResponseEntity.ok(usersService.deleteUser(userId));
//    }
//}
