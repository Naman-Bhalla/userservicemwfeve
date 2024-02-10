package com.scaler.userservicemwfeve.controllers;

import com.scaler.userservicemwfeve.dtos.LoginRequestDto;
import com.scaler.userservicemwfeve.dtos.LogoutRequestDto;
import com.scaler.userservicemwfeve.dtos.SignUpRequestDto;
import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.exception.UserNotFoundException;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody()LoginRequestDto loginRequestDto) throws UserNotFoundException {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        ResponseEntity<Token> userResponseEntity =
                new ResponseEntity<>(userService.login(email, password),HttpStatus.OK);

        // check if email and password in db
        // if yes return user
        // else throw some error
        return userResponseEntity;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody() SignUpRequestDto requestDto) {

        String email  = requestDto.getEmail();
        String password = requestDto.getPassword();
        String name = requestDto.getName();


        ResponseEntity<User> userResponseEntity =
                new ResponseEntity<>(userService.signUp(email, password, name), HttpStatus.OK);
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        return userResponseEntity;
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody()LogoutRequestDto logoutRequestDto) throws TokenNotExistException {
            userService.logOut(logoutRequestDto.getToken());
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<User> validateToken(@PathVariable("token") String token) throws TokenNotExistException {

        ResponseEntity<User> responseEntity = new ResponseEntity<>(userService.validateToken(token), HttpStatus.OK);
        return responseEntity;
    }

}

