package com.example.demo.service;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.entity.Recommendation;

public interface RecommendationService {

    Recommendation generateRecommendation(Long userId,
                                          RecommendationRequest request);
}
