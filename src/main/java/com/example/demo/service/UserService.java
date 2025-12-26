package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;

import java.util.Optional;

public interface UserService {

    User register(User user);

    AuthResponse login(String email, String password);

    Optional<User> findByEmail(String email);
}
