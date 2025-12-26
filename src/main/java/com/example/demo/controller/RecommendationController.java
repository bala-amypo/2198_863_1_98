package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/recommendations")
@Tag(name = "Recommendations", description = "Recommendation endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class RecommendationController {
    
    @Autowired
    private RecommendationService recommendationService;
    
    @PostMapping("/generate")
    @Operation(summary = "Generate recommendations for a user")
    public ResponseEntity<ApiResponse> generateRecommendation(@RequestParam Long userId,
                                                             @RequestBody RecommendationRequest request) {
        Recommendation recommendation = recommendationService.generateRecommendation(userId, request);
        return ResponseEntity.ok(new ApiResponse(true, "Recommendation generated successfully", recommendation));
    }
    
    @GetMapping("/latest")
    @Operation(summary = "Get latest recommendation for a user")
    public ResponseEntity<Recommendation> getLatestRecommendation(@RequestParam Long userId) {
        Recommendation recommendation = recommendationService.getLatestRecommendation(userId);
        return ResponseEntity.ok(recommendation);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get recommendations for a user within date range")
    public ResponseEntity<List<Recommendation>> getRecommendations(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<Recommendation> recommendations = recommendationService.getRecommendations(userId, from, to);
        return ResponseEntity.ok(recommendations);
    }
}