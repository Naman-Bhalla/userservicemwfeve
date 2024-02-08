package com.scaler.userservicemwfeve.services;
import com.scaler.userservicemwfeve.configs.BcryptConfiguration;
import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.exception.UserNotFoundException;
import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.repositories.TokenRepository;
import com.scaler.userservicemwfeve.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    public final UserRepository userRepository;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;


    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException {

        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("email or password is incorrect");
        }

        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new UserNotFoundException(" password is incorrect");
        }

        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(32));
        token.setExpiryAt(getExpiryAt());

        return tokenRepository.save(token);

    }

    public static Date getExpiryAt(){

        Calendar calendar = Calendar.getInstance();
        // Add 30 days to the current date
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        // Get the date after adding 30 days
        return calendar.getTime();

    }

    @Override
    public User signUp(String  email, String password, String name) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public void logOut(String tkn) throws TokenNotExistException {

        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedEquals(tkn, false);

        if(optionalToken.isPresent()){

            Token token = optionalToken.get();
            token.setDeleted(true);
            tokenRepository.save(token);
        }
        else {
            throw new TokenNotExistException("invalid token");
        }

//        Token token = optionalToken.get();
//        token.setDeleted(true);
//        tokenRepository.save(token);
    }
}



