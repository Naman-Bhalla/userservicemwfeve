package com.scaler.userservicemwfeve.controlleradvices;

import com.scaler.userservicemwfeve.dtos.ExceptionDto;
import com.scaler.userservicemwfeve.exception.TokenNotExistException;
import com.scaler.userservicemwfeve.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException userNotFoundException) {

        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(userNotFoundException.getMessage());
        exceptionDto.setDetails("user details entered not exist iun the system");

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenNotExistException.class)
    public ResponseEntity<ExceptionDto> handleTokenNotExistException(TokenNotExistException tokenNotExistException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(tokenNotExistException.getMessage());
        exceptionDto.setDetails("token provided is not valid for login session");

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

}
