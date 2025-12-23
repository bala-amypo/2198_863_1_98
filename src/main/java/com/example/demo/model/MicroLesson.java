package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "micro_lessons")
public class MicroLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private Integer durationMinutes;
    private String contentType;
    private String difficulty;
    private String tags;
    private LocalDate publishDate;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    public MicroLesson() {}
    
    public MicroLesson(String title, Integer durationMinutes, String contentType, String difficulty, Course course) {
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.contentType = contentType;
        this.difficulty = difficulty;
        this.course = course;
        this.publishDate = LocalDate.now();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    
    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }
    
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}