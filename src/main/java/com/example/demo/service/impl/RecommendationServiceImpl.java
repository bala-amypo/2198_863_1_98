package com.example.demo.service.impl;

import com.example.demo.entity.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository repo;

    public RecommendationServiceImpl(RecommendationRepository repo) {
        this.repo = repo;
    }

    public Recommendation saveRecommendation(Recommendation recommendation) {
        return repo.save(recommendation);
    }

    public List<Recommendation> getAllRecommendations() {
        return repo.findAll();
    }

    public Recommendation getRecommendationById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recommendation not found"));
    }

    public void deleteRecommendation(Long id) {
        repo.deleteById(id);
    }
}
