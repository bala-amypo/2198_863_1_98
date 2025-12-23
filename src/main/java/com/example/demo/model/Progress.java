package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @Column(nullable = false)
    private String status;

    private Integer progressPercent;

    
    private BigDecimal score;
    private LocalDateTime lastAccessedAt;

    @PrePersist
    public void onCreate() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
