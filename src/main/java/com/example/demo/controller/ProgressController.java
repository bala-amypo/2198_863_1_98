package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
@Tag(name = "Progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{lessonId}")
    public Progress record(@RequestParam Long userId,
                           @PathVariable Long lessonId,
                           @RequestBody Progress progress) {
        return progressService.recordProgress(userId, lessonId, progress);
    }

    @GetMapping("/lesson/{lessonId}")
    public Progress get(@RequestParam Long userId,
                        @PathVariable Long lessonId) {
        return progressService.getProgress(userId, lessonId);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> userProgress(@PathVariable Long userId) {
        return progressService.getUserProgress(userId);
    }
}
