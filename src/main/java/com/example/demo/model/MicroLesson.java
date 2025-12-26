package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "micro_lessons")
public class MicroLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contentType;
    private String difficulty;
    private String tags;
    private Integer durationMinutes;

    @ManyToOne
    private Course course;

    // -------- Getters & Setters --------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
