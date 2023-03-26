package com.example.elonuz.config.security;

import com.example.elonuz.domains.User;
import com.example.elonuz.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUserDetailsService implements UserDetailsService {
    private final UserRepository authUserRepository;

    public AuthUserUserDetailsService(UserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User authUser = authUserRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
        return new AuthUserUserDetails(authUser);
    }
}
