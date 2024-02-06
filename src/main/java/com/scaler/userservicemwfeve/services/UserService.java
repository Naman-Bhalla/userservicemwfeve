package com.scaler.userservicemwfeve.services;
import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.exception.UserNotFoundException;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService implements IUserService {

    public final UserRepository userRepository;
    public final TokenService tokenService;


    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> login(User user) throws UserNotFoundException {

        Optional<User> optionalUser = userRepository.findUserByEmailAndAndPassword(user.getEmail(), user.getPassword());

        if(optionalUser.isPresent()){
            tokenService.generateToken(optionalUser.get());
        }

        else {
             throw new UserNotFoundException("User email or password is not valid");
        }
        return optionalUser;

    }

    @Override
    public User signUp(User user) {
        return userRepository.save(user);
    }

    @Override
    public void logOut(String value) throws TokenNotExistException {
        tokenService.findByValue(value);
        tokenService.deleteToken(value);
    }
}



