package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController { // renamed from FakeWhitelabelController

    @GetMapping("/")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String fakeWhitelabel() {
        // Simulate Spring Boot Whitelabel Error Page
        return "<html>" +
                "<body>" +
                "<h1>Whitelabel Error Page</h1>" +
                "<p>This application has no explicit mapping for /, so you are seeing this as a fallback.</p>" +
                "<p>There was an unexpected error (type=Not Found, status=500).</p>" +
                "<p>No message available</p>" +
                "</body>" +
                "</html>";
    }
}
