package com.example.demo.service;

import com.example.demo.entity.Recommendation;

import java.util.List;

public interface RecommendationService {

    Recommendation generateRecommendation(Long userId);

    Recommendation getLatestRecommendation(Long userId);

    List<Recommendation> getRecommendations(Long userId);
}
