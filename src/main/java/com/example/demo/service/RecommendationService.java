package com.example.demo.service;

import com.example.demo.entity.Recommendation;
import java.util.List;

public interface RecommendationService {
    Recommendation saveRecommendation(Recommendation recommendation);
    List<Recommendation> getAllRecommendations();
    Recommendation getRecommendationById(Long id);
    void deleteRecommendation(Long id);
}
