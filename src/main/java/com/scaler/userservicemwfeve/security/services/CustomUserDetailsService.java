package com.scaler.userservicemwfeve.security.services;

import com.scaler.userservicemwfeve.models.User;
import com.scaler.userservicemwfeve.repositories.UserRepository;
import com.scaler.userservicemwfeve.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("username is not found with email" + username);
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(optionalUser.get());

        return customUserDetails;
    }
}
