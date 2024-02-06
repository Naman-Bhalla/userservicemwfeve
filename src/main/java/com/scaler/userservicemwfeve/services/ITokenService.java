package com.scaler.userservicemwfeve.services;

import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.exception.UserNotFoundException;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;

import java.util.Optional;

public interface ITokenService {

    void generateToken(User user) throws UserNotFoundException;
    Optional<Token> findByValue(String value) throws TokenNotExistException;

    void deleteToken(String value);
}
