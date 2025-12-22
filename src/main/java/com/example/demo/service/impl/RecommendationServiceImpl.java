package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;

    @Override
    public Recommendation generateRecommendation(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .recommendedLessonIds("1,2,3")
                .basisSnapshot("Basic recommendation logic")
                .confidenceScore(BigDecimal.valueOf(0.85))
                .build();

        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        return recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No recommendations found"));
    }

    @Override
    public List<Recommendation> getRecommendations(Long userId) {
        return recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);
    }
}
