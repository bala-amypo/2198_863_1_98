package com.example.demo.service.impl;

import com.example.demo.entity.Progress;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository repo;

    public ProgressServiceImpl(ProgressRepository repo) {
        this.repo = repo;
    }

    public Progress saveProgress(Progress progress) {
        return repo.save(progress);
    }

    public List<Progress> getAllProgress() {
        return repo.findAll();
    }

    public Progress getProgressById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
    }
}
