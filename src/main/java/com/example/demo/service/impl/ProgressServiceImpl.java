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
    public Progress addProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress getProgressById(Long id) {
        return progressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }
}
