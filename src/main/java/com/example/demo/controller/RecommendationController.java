package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.entity.Recommendation;
import com.example.demo.service.RecommendationService;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/{userId}")
    public Recommendation generateRecommendation(@PathVariable Long userId,
                                                 @RequestBody RecommendationRequest request) {
        return recommendationService.generateRecommendation(userId, request);
    }
}
