package com.example.demo.service.impl;

import com.example.demo.model.Progress;
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

    @Override
    public Progress create(Progress progress) {
        return repo.save(progress);
    }

    @Override
    public List<Progress> getAll() {
        return repo.findAll();
    }
}
