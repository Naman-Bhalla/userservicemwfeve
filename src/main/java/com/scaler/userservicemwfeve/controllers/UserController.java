package com.scaler.userservicemwfeve.controllers;

import com.scaler.userservicemwfeve.dtos.LoginRequestDto;
import com.scaler.userservicemwfeve.dtos.LogoutRequestDto;
import com.scaler.userservicemwfeve.dtos.SignUpRequestDto;
import com.scaler.userservicemwfeve.exceptions.UserAlreadyExistException;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public User login(@RequestBody LoginRequestDto loginRequestDto){
        // check if email and password in db
        // if yes return user
        // else throw some error
        try{
            return userService.login(loginRequestDto);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }
    @PostMapping("/signUp")
    public  ResponseEntity<User> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException {
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        return new ResponseEntity<>(userService.signUp(signUpRequestDto), HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto){
        // delete token if exists -> 200
        // if doesn't exist give a 404
        try{
            userService.logout(logoutRequestDto.getToken());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
