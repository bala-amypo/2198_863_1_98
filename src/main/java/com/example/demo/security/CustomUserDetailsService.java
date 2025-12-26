package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, UserPrincipal> users = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public UserPrincipal register(String email, String password, String role) {
        UserPrincipal principal =
                new UserPrincipal(idGen.getAndIncrement(), email, password, role);
        users.put(email, principal);
        return principal;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return users.get(username);
    }
}
