package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "micro_lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MicroLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Integer durationMinutes;

    private String contentType; // VIDEO, TEXT

    private String difficulty; // BEGINNER, INTERMEDIATE, ADVANCED

    private String tags;

    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
