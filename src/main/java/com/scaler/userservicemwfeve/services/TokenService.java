package com.scaler.userservicemwfeve.services;

import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.repositories.TokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class TokenService implements ITokenService{

    public final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void generateToken(User user)  {

        Token token = new Token();

        token.setValue(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        token.setUser(user);
        tokenRepository.save(token);

    }

    @Override
    public Optional<Token> findByValue(String value) throws TokenNotExistException {

        Optional<Token> optionalToken = tokenRepository.findByValue(value);

        if(optionalToken.isEmpty()){
            throw new TokenNotExistException("Invalid Token");
        }

        return optionalToken;
    }

    @Transactional
    @Override
    public void deleteToken(String value) {
        tokenRepository.deleteTokenByValue(value);
    }
}
