package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MicroLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private String title;

    private Integer durationMinutes;

    private String contentType; // VIDEO / ARTICLE / QUIZ

    private String difficulty; // BEGINNER / INTERMEDIATE / ADVANCED

    private String tags;

    private LocalDate publishDate;
}
