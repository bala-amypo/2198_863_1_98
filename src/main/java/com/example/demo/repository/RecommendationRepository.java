package com.example.demo.repository;

import com.example.demo.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(Long userId);

    List<Recommendation> findByUserIdAndGeneratedAtBetween(
            Long userId,
            LocalDateTime from,
            LocalDateTime to
    );
}
