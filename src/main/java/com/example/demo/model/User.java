// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDateTime;
// import java.util.List;

// @Entity
// @Table(name = "users")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, length = 100)
//     private String fullName;

//     @Column(unique = true, nullable = false)
//     private String email;

//     @Column(nullable = false)
//     private String password;

//     @Column(nullable = false)
//     private String role;

//     @Column(length = 50)
//     private String preferredLearningStyle;

//     @Column(nullable = false, updatable = false)
//     private LocalDateTime createdAt;

//     @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
//     private List<Course> courses;

//     @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//     private List<Progress> progressList;

//     @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//     private List<Recommendation> recommendations;

//     @PrePersist
//     public void onCreate() {
//         this.createdAt = LocalDateTime.now();
//     }
// }
