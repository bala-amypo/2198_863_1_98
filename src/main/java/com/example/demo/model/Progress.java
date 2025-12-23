package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String status = "NOT_STARTED";
    private Integer progressPercent = 0;
    private LocalDateTime lastAccessedAt;
    private BigDecimal score;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "micro_lesson_id")
    private MicroLesson microLesson;
    
    public Progress() {}
    
    public Progress(User user, MicroLesson microLesson, String status, Integer progressPercent) {
        this.user = user;
        this.microLesson = microLesson;
        this.status = status;
        this.progressPercent = progressPercent;
        this.lastAccessedAt = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        if (lastAccessedAt == null) {
            lastAccessedAt = LocalDateTime.now();
        }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Integer getProgressPercent() { return progressPercent; }
    public void setProgressPercent(Integer progressPercent) { this.progressPercent = progressPercent; }
    
    public LocalDateTime getLastAccessedAt() { return lastAccessedAt; }
    public void setLastAccessedAt(LocalDateTime lastAccessedAt) { this.lastAccessedAt = lastAccessedAt; }
    
    public BigDecimal getScore() { return score; }
    public void setScore(BigDecimal score) { this.score = score; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public MicroLesson getMicroLesson() { return microLesson; }
    public void setMicroLesson(MicroLesson microLesson) { this.microLesson = microLesson; }
}