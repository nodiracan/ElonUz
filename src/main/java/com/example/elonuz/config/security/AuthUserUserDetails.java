package com.example.elonuz.config.security;

import com.example.elonuz.domains.Permission;
import com.example.elonuz.domains.Role;
import com.example.elonuz.domains.User;
import com.example.elonuz.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class AuthUserUserDetails implements UserDetails {

    private final User authUser;

    public AuthUserUserDetails(User authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authRoles = Objects.requireNonNullElse(authUser.getRoles(), Collections.<Role>emptySet());
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        authRoles.forEach(authRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getCode()));
            Collection<Permission> authPermissions = Objects.requireNonNullElse(authRole.getPermissions(), Collections.emptySet());
            authPermissions.forEach(authPermission -> {
                authorities.add(new SimpleGrantedAuthority(authPermission.getCode()));
            });
        });
        return authorities;
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
