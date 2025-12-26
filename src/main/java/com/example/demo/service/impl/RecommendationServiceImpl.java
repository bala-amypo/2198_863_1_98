package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecommendationServiceImpl {

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

    public Recommendation getLatestRecommendation(Long userId) {

        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list.isEmpty()) {
            throw new RuntimeException("No recommendations");
        }

        return list.get(0);
    }

    public List<Recommendation> getRecommendationsInRange(
            Long userId,
            LocalDateTime from,
            LocalDateTime to) {

        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, from, to);
    }
}
