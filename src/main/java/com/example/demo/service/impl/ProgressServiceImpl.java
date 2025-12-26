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
    private final MicroLessonRepository microLessonRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository,
                               UserRepository userRepository,
                               MicroLessonRepository microLessonRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    @Override
    public Progress recordProgress(Long userId,
                                   Long lessonId,
                                   Progress incoming) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MicroLesson lesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Progress target = progressRepository
                .findByUserIdAndMicroLessonId(userId, lessonId)
                .orElseGet(Progress::new);

        target.setUser(user);
        target.setMicroLesson(lesson);
        target.setProgressPercent(incoming.getProgressPercent());
        target.setStatus(incoming.getStatus());
        target.setScore(incoming.getScore());

        return progressRepository.save(target);
    }

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
