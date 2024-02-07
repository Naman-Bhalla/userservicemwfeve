package com.scaler.userservicemwfeve.repositories;

import com.scaler.userservicemwfeve.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String token);
}
