package com.scaler.userservicemwfeve.services;

import com.scaler.userservicemwfeve.dtos.LoginRequestDto;
import com.scaler.userservicemwfeve.dtos.SignUpRequestDto;
import com.scaler.userservicemwfeve.exceptions.UserAlreadyExistException;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.repositories.SessionRepository;
import com.scaler.userservicemwfeve.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    public User signUp(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException {
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestDto.getEmail());
        if(optionalUser.isEmpty()){
            User newUser = new User();
            newUser.setId(1L);
            newUser.setName(signUpRequestDto.getName());
            newUser.setEmail(signUpRequestDto.getEmail());
            newUser.setHashedPassword(signUpRequestDto.getPassword());
            return userRepository.save(newUser);
        }
        else{
            throw new UserAlreadyExistException("User with email id: "+signUpRequestDto.getEmail() +" already exists!");
        }
    }

    public User login(LoginRequestDto loginRequestDto) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequestDto.getEmail());
        if(optionalUser.isEmpty()){
            throw new Exception("Invalid Email Address!");
        }
        else if(optionalUser.get().getHashedPassword().equals(loginRequestDto.getPassword())){
            return optionalUser.get();
        }
        else{
            throw new Exception("Wrong Password!");
        }
    }

    public void logout(String token) throws Exception{
        Optional<Token> optionalSession =  sessionRepository.findByValue(token);
        if(optionalSession.isEmpty()){
            throw new Exception("Session doesn't exist!");
        }
        else{
            sessionRepository.deleteById(optionalSession.get().getId());
        }
    }

}
