package com.scaler.userservicemwfeve.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservicemwfeve.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority() {}
    public CustomGrantedAuthority(Role role) {

        this.authority =  role.getName();
    }

    @Override
    public String getAuthority() {

        return authority;
    }
}
