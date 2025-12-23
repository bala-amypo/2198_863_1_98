package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import com.example.demo.dto.RecommendationRequest;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate")
    public Recommendation generate(@RequestBody RecommendationRequest request) {
        return recommendationService.generateRecommendation(request);
    }

    @GetMapping
    public List<Recommendation> getAll() {
        return recommendationService.getAllRecommendations();
    }
}