package com.scaler.userservicemwfeve.services;

import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.exception.UserNotFoundException;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;

import java.util.Optional;

public interface IUserService {

//    User login();

    Optional<User> login(User user) throws UserNotFoundException;

    User signUp(User user);

    void logOut(String value) throws TokenNotExistException;

}
