package com.example.demo.service;

import com.example.demo.entity.Progress;
import java.util.List;

public interface ProgressService {
    Progress saveProgress(Progress progress);
    List<Progress> getAllProgress();
    Progress getProgressById(Long id);
}
