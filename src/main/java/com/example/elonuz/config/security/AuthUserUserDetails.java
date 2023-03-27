package com.example.elonuz.config.security;

import com.example.elonuz.domains.User;
import com.example.elonuz.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AuthUserUserDetails implements UserDetails {

    private final User authUser;

    public AuthUserUserDetails(User authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + authUser.getRole()));
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }


    public User getAuthUser() {
        return authUser;
    }

    public Integer getId() {
        return authUser.getId();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !authUser.getStatus().equals(UserStatus.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.ACTIVE.equals(authUser.getStatus());
    }
}
