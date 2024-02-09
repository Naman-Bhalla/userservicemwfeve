package com.scaler.userservicemwfeve.dtos;

import com.scaler.userservicemwfeve.models.Role;
import com.scaler.userservicemwfeve.models.User;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;

    public static UserDto from(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.roles = user.getRoles();
        userDto.isEmailVerified = user.isEmailVerified();

        return userDto;
    }
}
