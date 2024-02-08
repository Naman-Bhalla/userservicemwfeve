package com.scaler.userservicemwfeve.repositories;

import com.scaler.userservicemwfeve.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

//    Token save(Token token);

    //    void deleteTokenByValueTrue(Boolean value);
   // Optional<Token> findByValueAndDeletedEquals(String value, boolean isDeleted);
    Optional<Token> findByValueAndDeletedEquals (String value, boolean isDeleted);
    Optional<Token> findByValue(String value);


}

