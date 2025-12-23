// package com.example.demo.service.impl;

// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;

//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     // REAL (needed for DB insert)
//     @Override
//     public User register(User user) {
//         if (user.getRole() == null) {
//             user.setRole("LEARNER");
//         }
//         return userRepository.save(user);
//     }

//     // DUMMY (required only to satisfy interface)
//     @Override
//     public AuthResponse login(String email, String password) {
//         return new AuthResponse();
//     }

//     // DUMMY
//     @Override
//     public User findById(Long id) {
//         return null;
//     }

//     // DUMMY
//     @Override
//     public User findByEmail(String email) {
//         return null;
//     }
// }
