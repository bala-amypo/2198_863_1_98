package com.example.demo.security;

import java.util.Map;

public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String subject) {
        return "generated-jwt-token";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }
}
