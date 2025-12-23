package com.example.demo.service.impl;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository repo;

    public RecommendationServiceImpl(RecommendationRepository repo) {
        this.repo = repo;
    }

    @Override
    public Recommendation generateRecommendation(RecommendationRequest request) {

        Recommendation recommendation = Recommendation.builder()
                .recommendedLessonIds("1,2,3")
                .basisSnapshot("demo")
                .confidenceScore(BigDecimal.valueOf(0.9))
                .build();

        return repo.save(recommendation);
    }

    @Override
    public List<Recommendation> getAllRecommendations() {
        return repo.findAll();
    }
}
