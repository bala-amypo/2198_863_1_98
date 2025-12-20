package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Progress;

public interface ProgressService {

    Progress saveProgress(Progress progress);

    List<Progress> getProgressForUser(Long userId);
}
