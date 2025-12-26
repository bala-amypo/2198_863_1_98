package com.example.demo.service.impl;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository,
                                     UserRepository userRepository,
                                     MicroLessonRepository microLessonRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    @Override
    public Recommendation generateRecommendation(Long userId,
                                                  RecommendationRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recommendation r = Recommendation.builder()
                .user(user)
                .basisSnapshot(request.getBasisSnapshot())
                .recommendedLessonIds(request.getRecommendedLessonIds())
                .confidenceScore(request.getConfidenceScore())
                .build();

        return recommendationRepository.save(r);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {

        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list.isEmpty()) {
            throw new RuntimeException("No recommendation");
        }

        return list.get(0);
    }
}
