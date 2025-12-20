package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Progress;
import com.example.demo.entity.User;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository,
                               UserRepository userRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Progress saveProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public List<Progress> getProgressForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user == null ? List.of() : progressRepository.findByUser(user);
    }
}
