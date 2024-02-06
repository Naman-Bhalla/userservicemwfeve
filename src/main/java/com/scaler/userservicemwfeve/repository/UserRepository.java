package com.scaler.userservicemwfeve.repository;

import com.scaler.userservicemwfeve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndHashedPassword(String email,String password);
}
