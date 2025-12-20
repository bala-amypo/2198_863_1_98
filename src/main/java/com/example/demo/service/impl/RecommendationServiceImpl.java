package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.entity.Recommendation;
import com.example.demo.entity.User;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository,
                                     UserRepository userRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Recommendation generateRecommendation(Long userId,
                                                 RecommendationRequest request) {

        User user = userRepository.findById(userId).orElse(null);

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .recommendedLessonIds("1,2,3")
                .basisSnapshot(request.getLearningGoal())
                .confidenceScore(null)
                .build();

        return recommendationRepository.save(recommendation);
    }
}
