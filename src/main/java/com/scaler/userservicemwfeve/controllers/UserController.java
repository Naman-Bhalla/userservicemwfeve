package com.scaler.userservicemwfeve.controllers;

import com.scaler.userservicemwfeve.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    public User login() {
        res=True
        String email=userRepo.getUserByID(id);
        if (email==None){
            throw new Exception;
        }

        String passWord=userRepo.getPasswordById(id);
        if (passWord==None OR passWord==actualPassword){
            throw new Exception;
        }

        ResponseEntity<Boolean> response= new Response();
        response.set(Response.200);
        return response;



    }

    public User signUp() {
        User user=userRepo.getUserByEmail(email);
        if(user!=None){
            throw new Exception;
        }
        User user=unew User();
        user.setName(name);
        user.setEmail(email);
        user.password(password);
        User user=userRepo.save(user);
        return new ResponseEntity(Response.200)
    }

    public ResponseEntity<Void> logout() {
        Token token = tokemnRepo.getToken(token);
        if (token==None){
            return new ResponseEntity(Response.400)

        }
        tokenRepo.deleteByToken(token);

    }
}
