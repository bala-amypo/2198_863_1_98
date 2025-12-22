package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAccount register(UserAccount user) {
        return userRepository.save(user);
    }

    @Override
    public UserAccount getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }
}
