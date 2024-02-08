package com.scaler.userservicemwfeve.repositories;

import com.scaler.userservicemwfeve.models.Token;
import com.scaler.userservicemwfeve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Token save(Token token);

    Optional<User> findUserByEmail(String email);



}
