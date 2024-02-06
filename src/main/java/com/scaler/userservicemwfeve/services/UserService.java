package com.scaler.userservicemwfeve.services;

import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.repository.TokenRepository;
import com.scaler.userservicemwfeve.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmailAndHashedPassword(email,password);
    }

    public User signUp(String email,String password,String name) {
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setHashedPassword(password);
        user.setName(name);
        return userRepository.save(user);
    }

    public ResponseEntity<Void> logout(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValue(token);
        if (tokenOptional.isPresent()) {
            tokenRepository.deleteById(tokenOptional.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
