package com.example.demo.service.impl;

import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    @Override
    public Progress getUserProgress(Long id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found with id: " + id));
    }

    @Override
    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress updateProgress(Long id, Progress progressDetails) {
        Progress progress = getUserProgress(id);
        progress.setUser(progressDetails.getUser());
        progress.setMicroLesson(progressDetails.getMicroLesson());
        progress.setStatus(progressDetails.getStatus());
        progress.setProgressPercent(progressDetails.getProgressPercent());
        progress.setScore(progressDetails.getScore());
        return progressRepository.save(progress);
    }

    @Override
    public void deleteProgress(Long id) {
        Progress progress = getUserProgress(id);
        progressRepository.delete(progress);
    }
}
