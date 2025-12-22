package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

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

    private String status; // NOT_STARTED / IN_PROGRESS / COMPLETED

    private Integer progressPercent;

    private LocalDateTime lastAccessedAt;

    private BigDecimal score;

    @PrePersist
    public void onAccess() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
