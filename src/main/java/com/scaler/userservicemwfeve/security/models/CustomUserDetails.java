package com.scaler.userservicemwfeve.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservicemwfeve.models.Role;
import com.scaler.userservicemwfeve.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {


    private List<CustomGrantedAuthority> authorities;

    private Long userId;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CustomUserDetails(){}

    public CustomUserDetails(User user){

        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.enabled = true;
        this.credentialsNonExpired = true;
        this.password = user.getHashedPassword();
        this.username = user.getEmail();
        this.userId = user.getId();


        List<CustomGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Role role: user.getRoles()) {
            grantedAuthorities.add(new CustomGrantedAuthority(role));
        }

        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
