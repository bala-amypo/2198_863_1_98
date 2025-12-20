package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "micro_lesson_id", nullable = false)
    private MicroLesson microLesson;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Integer progressPercent; // 0-100

    @Column(nullable = false)
    private LocalDateTime lastAccessedAt;

    private Double score; // nullable

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastAccessedAt = LocalDateTime.now();
        if (status == Status.COMPLETED) {
            progressPercent = 100;
        }
    }
}

enum Status {
    NOT_STARTED, IN_PROGRESS, COMPLETED
}