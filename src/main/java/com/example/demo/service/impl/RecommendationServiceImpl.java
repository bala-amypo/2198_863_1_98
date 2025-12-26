package com.example.demo.service.impl;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MicroLessonRepository microLessonRepository;

    @Autowired
    private ProgressRepository progressRepository;

    @Override
    public Recommendation generateRecommendation(Long userId, RecommendationRequest params) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Progress> userProgress =
                progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);

        // ✅ FIX: Convert List<String> → String ONCE
        String tags = (params.getTags() != null && !params.getTags().isEmpty())
                ? String.join(",", params.getTags())
                : null;

        List<MicroLesson> candidateLessons = microLessonRepository.findByFilters(
                tags,
                params.getTargetDifficulty(),
                params.getContentType()
        );

        // Filter out completed lessons
        List<Long> completedLessonIds = userProgress.stream()
                .filter(p -> "COMPLETED".equals(p.getStatus()))
                .map(p -> p.getMicroLesson().getId())
                .collect(Collectors.toList());

        List<MicroLesson> recommendedLessons = candidateLessons.stream()
                .filter(lesson -> !completedLessonIds.contains(lesson.getId()))
                .limit(params.getMaxItems() != null ? params.getMaxItems() : 5)
                .collect(Collectors.toList());

        String recommendedLessonIds = recommendedLessons.stream()
                .map(lesson -> lesson.getId().toString())
                .collect(Collectors.joining(","));

        // ✅ FIX: Use converted `tags` string
        String basisSnapshot = String.format(
                "{\"completedLessons\":%d,\"filters\":{\"difficulty\":\"%s\",\"tags\":\"%s\",\"contentType\":\"%s\"}}",
                completedLessonIds.size(),
                params.getTargetDifficulty(),
                tags,
                params.getContentType()
        );

        BigDecimal confidenceScore =
                calculateConfidenceScore(recommendedLessons.size(), userProgress.size());

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .recommendedLessonIds(recommendedLessonIds)
                .basisSnapshot(basisSnapshot)
                .confidenceScore(confidenceScore)
                .build();

        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> recommendations =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (recommendations.isEmpty()) {
            throw new ResourceNotFoundException("No recommendations found");
        }
        return recommendations.get(0);
    }

    @Override
    public List<Recommendation> getRecommendations(Long userId, LocalDate from, LocalDate to) {
        LocalDateTime startDateTime = from.atStartOfDay();
        LocalDateTime endDateTime = to.atTime(23, 59, 59);
        return recommendationRepository.findByUserIdAndGeneratedAtBetween(
                userId, startDateTime, endDateTime
        );
    }

    private BigDecimal calculateConfidenceScore(int recommendedCount, int progressCount) {
        if (recommendedCount == 0) {
            return BigDecimal.ZERO;
        }

        double score = Math.min(1.0, (double) recommendedCount / 5.0);
        if (progressCount > 0) {
            score = Math.min(1.0, score + (double) progressCount / 20.0);
        }

        return BigDecimal.valueOf(Math.max(0.1, Math.min(1.0, score)));
    }
}
