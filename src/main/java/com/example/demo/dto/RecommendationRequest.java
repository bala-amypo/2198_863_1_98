package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationRequest {
    private String learningGoal;
    private String preferredDifficulty;
    private Integer maxDurationMinutes;
}
