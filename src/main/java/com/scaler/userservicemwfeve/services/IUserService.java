package com.scaler.userservicemwfeve.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.exception.UserNotFoundException;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;

import java.util.Optional;

public interface IUserService {

//    User login();

    Token login(String email, String password) throws UserNotFoundException;

    User signUp(String name, String email, String password) throws JsonProcessingException;

    void logOut(String value) throws TokenNotExistException;

    User validateToken(String token) throws TokenNotExistException;
}
