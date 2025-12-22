package com.example.demo.service.impl;

import com.example.demo.entity.Progress;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public Progress saveProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    @Override
    public Progress getProgressById(Long id) {
        return progressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Progress not found with id: " + id));
    }

    @Override
    public void deleteProgress(Long id) {
        progressRepository.deleteById(id);
    }
}
