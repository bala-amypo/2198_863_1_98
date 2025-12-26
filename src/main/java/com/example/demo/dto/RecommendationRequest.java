package com.example.demo.dto;

import java.util.List;

public class RecommendationRequest {

    private Long userId;
    private List<Long> completedLessonIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getCompletedLessonIds() {
        return completedLessonIds;
    }

    public void setCompletedLessonIds(List<Long> completedLessonIds) {
        this.completedLessonIds = completedLessonIds;
    }
}
