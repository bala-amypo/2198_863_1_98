package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 1000)
    private String recommendedLessonIds;

    @Column(length = 2000)
    private String basisSnapshot;

    private BigDecimal confidenceScore;
    private LocalDateTime generatedAt;

    @PrePersist
    public void prePersist() {
        this.generatedAt = LocalDateTime.now();
    }

    // -------- Getters & Setters --------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getRecommendedLessonIds() { return recommendedLessonIds; }
    public void setRecommendedLessonIds(String recommendedLessonIds) { this.recommendedLessonIds = recommendedLessonIds; }

    public String getBasisSnapshot() { return basisSnapshot; }
    public void setBasisSnapshot(String basisSnapshot) { this.basisSnapshot = basisSnapshot; }

    public BigDecimal getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(BigDecimal confidenceScore) { this.confidenceScore = confidenceScore; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
}
