package com.example.demo.security;

public class JwtTokenProvider {

    private final String secret;
    private final long expirationMs;

    public JwtTokenProvider(String secret, long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    public String generateToken(UserPrincipal principal) {
        return "token-" + principal.getUsername();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("token-");
    }

    public String getUsernameFromToken(String token) {
        if (token == null) return null;
        return token.replace("token-", "");
    }
}
