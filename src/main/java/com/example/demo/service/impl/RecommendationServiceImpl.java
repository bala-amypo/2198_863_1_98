package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.RecommendationService;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository lessonRepository;

    @Override
    public Recommendation generateRecommendation(Long userId, String tags, String difficulty) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<MicroLesson> lessons = lessonRepository.findByTagsContainingAndDifficultyAndContentType(
                tags, difficulty, "VIDEO"
        );

        String lessonIds = lessons.stream()
                .map(l -> l.getId().toString())
                .collect(Collectors.joining(","));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .recommendedLessonIds(lessonIds)
                .basisSnapshot("{\"tags\":\"" + tags + "\",\"difficulty\":\"" + difficulty + "\"}")
                .confidenceScore(lessons.isEmpty() ? null : java.math.BigDecimal.valueOf(0.8))
                .build();

        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list = recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);
        if (list.isEmpty()) throw new ResourceNotFoundException("No recommendations found");
        return list.get(0);
    }

    @Override
    public List<Recommendation> getRecommendations(Long userId, LocalDate from, LocalDate to) {
        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.atTime(23, 59, 59);
        return recommendationRepository.findByUserIdAndGeneratedAtBetween(userId, start, end);
    }
}
