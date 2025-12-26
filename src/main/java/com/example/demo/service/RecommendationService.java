package com.example.demo.service;

import com.example.demo.model.Recommendation;

import java.util.List;

public interface RecommendationService {

    Recommendation generateRecommendation(Long userId);

    Recommendation getLatestRecommendation(Long userId);

    List<Recommendation> getRecommendationsBetween(Long userId);
}
