package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    @Column(columnDefinition = "TEXT")
    private String recommendedLessonIds; // comma-separated or JSON

    @Column(columnDefinition = "TEXT")
    private String basisSnapshot;

    @Column(nullable = false)
    private Double confidenceScore; // 0.0 to 1.0

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }
}