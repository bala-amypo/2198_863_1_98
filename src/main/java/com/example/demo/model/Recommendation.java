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
    
    private LocalDateTime generatedAt;
    private String recommendedLessonIds;
    private String basisSnapshot;
    private BigDecimal confidenceScore;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public Recommendation() {}
    
    public Recommendation(User user, String recommendedLessonIds, String basisSnapshot, BigDecimal confidenceScore) {
        this.user = user;
        this.recommendedLessonIds = recommendedLessonIds;
        this.basisSnapshot = basisSnapshot;
        this.confidenceScore = confidenceScore;
        this.generatedAt = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
    
    public String getRecommendedLessonIds() { return recommendedLessonIds; }
    public void setRecommendedLessonIds(String recommendedLessonIds) { this.recommendedLessonIds = recommendedLessonIds; }
    
    public String getBasisSnapshot() { return basisSnapshot; }
    public void setBasisSnapshot(String basisSnapshot) { this.basisSnapshot = basisSnapshot; }
    
    public BigDecimal getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(BigDecimal confidenceScore) { this.confidenceScore = confidenceScore; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}