package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository lessonRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository,
                               UserRepository userRepository,
                               MicroLessonRepository lessonRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Progress recordProgress(Long userId, Long microLessonId, Progress incoming) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MicroLesson lesson = lessonRepository.findById(microLessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        return progressRepository
                .findByUserIdAndMicroLessonId(userId, microLessonId)
                .map(existing -> {
                    existing.setProgressPercent(incoming.getProgressPercent());
                    existing.setStatus(incoming.getStatus());
                    existing.setScore(incoming.getScore());
                    return progressRepository.save(existing);
                })
                .orElseGet(() -> {
                    incoming.setUser(user);
                    incoming.setMicroLesson(lesson);
                    return progressRepository.save(incoming);
                });
    }

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
