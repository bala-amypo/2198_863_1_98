package com.example.demo.controller;

import com.example.demo.entity.Recommendation;
import com.example.demo.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate/{userId}")
    public Recommendation generate(@PathVariable Long userId) {
        return recommendationService.generateRecommendation(userId);
    }

    @GetMapping("/latest/{userId}")
    public Recommendation getLatest(@PathVariable Long userId) {
        return recommendationService.getLatestRecommendation(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getAll(@PathVariable Long userId) {
        return recommendationService.getRecommendations(userId);
    }
}
