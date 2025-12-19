package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationRequest {

    private String preferredTags;
    private String difficulty;
    private Integer maxDuration;
}
