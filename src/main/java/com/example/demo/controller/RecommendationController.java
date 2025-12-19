package com.example.demo.controller;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@Tag(name = "Recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate")
    public Recommendation generate(@RequestParam Long userId,
                                   @RequestBody RecommendationRequest request) {
        return recommendationService.generateRecommendation(userId, request);
    }

    @GetMapping("/latest")
    public Recommendation latest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendation(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> list(@PathVariable Long userId) {
        return recommendationService.getRecommendations(userId, null, null);
    }
}
