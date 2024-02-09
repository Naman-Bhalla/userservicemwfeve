package com.scaler.userservicemwfeve.controllers;

import com.scaler.userservicemwfeve.dtos.LoginRequestDto;
import com.scaler.userservicemwfeve.dtos.LogoutRequestDto;
import com.scaler.userservicemwfeve.dtos.SignUpRequestDto;
import com.scaler.userservicemwfeve.dtos.UserDto;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto request) {
        // check if email and password in db
        // if yes return user
        // else throw some error
        return userService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto request) {
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();;


        return UserDto.from(userService.signUp(name, email, password));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) {
        // delete token if exists -> 200
        // if doesn't exist give a 404

        userService.logout(request.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NonNull String token) {
        return UserDto.from(userService.validateToken(token));
    }
}

// Break till 10:35