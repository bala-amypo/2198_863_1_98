package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime generatedAt;

    private String recommendedLessonIds;

    private String basisSnapshot;

    private Double confidenceScore;

    @PrePersist
    void onGenerate() {
        generatedAt = LocalDateTime.now();
    }
}
