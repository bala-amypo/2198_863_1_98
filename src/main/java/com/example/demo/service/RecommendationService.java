package com.example.demo.service;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;

import java.time.LocalDate;
import java.util.List;

public interface RecommendationService {

    Recommendation generateRecommendation(RecommendationRequest request);

    List<Recommendation> getAllRecommendations();
}
