package com.example.demo.service.impl;

import com.example.demo.entity.Progress;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Override
    public Progress getUserProgress(Long id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found with id " + id));
    }

    @Override
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    @Override
    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress updateProgress(Long id, Progress progressDetails) {
        Progress progress = getUserProgress(id);
        progress.setCompletedLessons(progressDetails.getCompletedLessons());
        return progressRepository.save(progress);
    }

    @Override
    public void deleteProgress(Long id) {
        Progress progressRepositoryEntity = getUserProgress(id);
        progressRepository.delete(progressRepositoryEntity);
    }
}
