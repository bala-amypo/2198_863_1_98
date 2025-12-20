package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "micro_lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MicroLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer durationMinutes; // > 0, typically <= 15

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Column(columnDefinition = "TEXT")
    private String tags; // comma-separated or JSON

    @Column(nullable = false)
    private LocalDate publishDate;
}

enum ContentType {
    VIDEO, TEXT, QUIZ, INTERACTIVE
}

enum Difficulty {
    EASY, MEDIUM, HARD
}