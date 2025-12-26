package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService {
    
    @Autowired
    private ProgressRepository progressRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MicroLessonRepository microLessonRepository;
    
    @Override
    public Progress recordProgress(Long userId, Long lessonId, Progress progress) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        MicroLesson lesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
        
        if (progress.getProgressPercent() < 0 || progress.getProgressPercent() > 100) {
            throw new IllegalArgumentException("Progress percent must be between 0 and 100");
        }
        
        if ("COMPLETED".equals(progress.getStatus()) && progress.getProgressPercent() != 100) {
            throw new IllegalArgumentException("Completed status requires 100% progress");
        }
        
        Optional<Progress> existingProgress = progressRepository.findByUserIdAndMicroLessonId(userId, lessonId);
        
        Progress progressToSave;
        if (existingProgress.isPresent()) {
            progressToSave = existingProgress.get();
            progressToSave.setStatus(progress.getStatus());
            progressToSave.setProgressPercent(progress.getProgressPercent());
            if (progress.getScore() != null) {
                progressToSave.setScore(progress.getScore());
            }
        } else {
            progressToSave = Progress.builder()
                    .user(user)
                    .microLesson(lesson)
                    .status(progress.getStatus())
                    .progressPercent(progress.getProgressPercent())
                    .score(progress.getScore())
                    .build();
        }
        
        return progressRepository.save(progressToSave);
    }
    
    @Override
    public Progress getProgress(Long userId, Long lessonId) {
        return progressRepository.findByUserIdAndMicroLessonId(userId, lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found"));
    }
    
    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}