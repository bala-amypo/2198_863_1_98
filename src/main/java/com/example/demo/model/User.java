package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // Will be hashed with BCrypt

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role; // LEARNER, INSTRUCTOR, ADMIN

    private String preferredLearningStyle;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (role == null) {
            role = Role.LEARNER; // Default role
        }
    }
}

enum Role {
    LEARNER, INSTRUCTOR, ADMIN
}