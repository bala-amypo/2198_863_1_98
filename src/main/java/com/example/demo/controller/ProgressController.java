package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{lessonId}")
    public Progress record(@PathVariable Long lessonId, @RequestBody Progress progress) {
        return progressService.recordProgress(null, lessonId, progress);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> userProgress(@PathVariable Long userId) {
        return progressService.getUserProgress(userId);
    }
}
