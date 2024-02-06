package com.scaler.userservicemwfeve.controllers;

import com.scaler.userservicemwfeve.dtos.LoginRequestDto;
import com.scaler.userservicemwfeve.dtos.LogoutRequestDto;
import com.scaler.userservicemwfeve.dtos.SignUpRequestDto;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public User login(@RequestBody LoginRequestDto loginRequestDto) {
        // check if email and password in db
        // if yes return user
        // else throw some error
        Optional<User> userOptional = userService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not present with entered email & password");
        }
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        return userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getName());
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        // delete token if exists -> 200
        // if doesn't exist give a 404
        return userService.logout(logoutRequestDto.getToken());
    }
}
