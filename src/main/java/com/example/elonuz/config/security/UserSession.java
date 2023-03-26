package com.example.elonuz.config.security;
import com.example.elonuz.domains.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    public User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        var authUserDetails = authentication.getPrincipal();
        if (authUserDetails instanceof AuthUserUserDetails a)
            return a.getAuthUser();
        return null;
    }

    public Integer getId() {
        User user = getUser();
        if (user != null)
            return user.getId();
        return null;
    }
}
