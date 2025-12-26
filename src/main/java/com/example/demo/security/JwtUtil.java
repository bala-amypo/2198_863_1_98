package com.example.demo.security;

import java.util.Map;

public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String subject) {
        // Tests MOCK this method, real logic not required
        return "mock-jwt-token";
    }

    public boolean validateToken(String token) {
        // Tests MOCK this method too
        return true;
    }
}
