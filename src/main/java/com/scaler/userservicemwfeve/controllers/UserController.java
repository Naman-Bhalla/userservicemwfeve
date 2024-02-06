package com.scaler.userservicemwfeve.controllers;

import com.scaler.userservicemwfeve.dtos.LoginRequestDto;
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

    @GetMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody()LoginRequestDto loginRequestDto) throws UserNotFoundException {

        User user = getUserForLogin(loginRequestDto);
        ResponseEntity<Optional<User>> userResponseEntity =
                new ResponseEntity<>(userService.login(user),HttpStatus.OK);

        // check if email and password in db
        // if yes return user
        // else throw some error
        return userResponseEntity;
    }

    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody() SignUpRequestDto signUpRequestDto) {

        User user = getUserForSignUP(signUpRequestDto);

        ResponseEntity<User> userResponseEntity =
                new ResponseEntity<>(userService.signUp(user), HttpStatus.OK);
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        return userResponseEntity;
    }


    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam("tokenValue")String value) throws TokenNotExistException {
            userService.logOut(value);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    private User getUserForSignUP(SignUpRequestDto signUpRequestDto) {

        User user = new User();
        user.setName(signUpRequestDto.getName());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(signUpRequestDto.getPassword());

        return user;
    }
    private User getUserForLogin(LoginRequestDto loginRequestDto) {

        User user = new User();
        user.setEmail(loginRequestDto.getEmail());
        user.setPassword(loginRequestDto.getPassword());
        return user;
    }
}

