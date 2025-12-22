package com.example.demo.service;

import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import java.time.LocalDate;
import java.util.List;

public interface RecommendationService {
    Recommendation generateRecommendation(Long userId, String tags, String difficulty);
    Recommendation getLatestRecommendation(Long userId);
    List<Recommendation> getRecommendations(Long userId, LocalDate from, LocalDate to);
}
