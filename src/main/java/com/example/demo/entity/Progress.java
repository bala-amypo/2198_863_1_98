package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private MicroLesson microLesson;

    private String status; // NOT_STARTED, IN_PROGRESS, COMPLETED

    private Integer progressPercent;

    private LocalDateTime lastAccessedAt;

    private Double score;

    @PrePersist
    public void onUpdate() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
