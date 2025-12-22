package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public Recommendation generateRecommendation(Long userId) {
        Recommendation rec = Recommendation.builder()
                .recommendedLessonIds("1,2,3")
                .basisSnapshot("recent_activity")
                .confidenceScore(java.math.BigDecimal.valueOf(0.8))
                .build();
        return recommendationRepository.save(rec);
    }

    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list = recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Recommendation> getRecommendations(Long userId, LocalDate from, LocalDate to) {
        return recommendationRepository.findAll();
    }
}
