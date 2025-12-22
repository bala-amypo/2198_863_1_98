package com.example.demo.service.impl;

import com.example.demo.model.Progress;
import com.example.demo.model.MicroLesson;
import com.example.demo.model.User;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.UserRepository;
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
    private MicroLessonRepository lessonRepository;

    @Override
    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress updateProgress(Long progressId, Progress progressDetails) {
        Progress progress = progressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("Progress not found with id: " + progressId));

        progress.setStatus(progressDetails.getStatus());
        progress.setProgressPercent(progressDetails.getProgressPercent());
        progress.setScore(progressDetails.getScore());
        progress.setUser(progressDetails.getUser());
        progress.setMicroLesson(progressDetails.getMicroLesson());

        return progressRepository.save(progress);
    }

    @Override
    public void deleteProgress(Long progressId) {
        Progress progress = progressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("Progress not found with id: " + progressId));
        progressRepository.delete(progress);
    }

    @Override
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    @Override
    public Progress getProgressById(Long progressId) {
        return progressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("Progress not found with id: " + progressId));
    }
}
