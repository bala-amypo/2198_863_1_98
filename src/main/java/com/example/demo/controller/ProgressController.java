package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/progress")
@Tag(name = "Progress", description = "Progress tracking endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class ProgressController {
    
    @Autowired
    private ProgressService progressService;
    
    @PostMapping("/{lessonId}")
    @Operation(summary = "Record progress for a lesson")
    public ResponseEntity<ApiResponse> recordProgress(@PathVariable Long lessonId, 
                                                     @RequestParam Long userId,
                                                     @RequestBody Progress progress) {
        Progress recordedProgress = progressService.recordProgress(userId, lessonId, progress);
        return ResponseEntity.ok(new ApiResponse(true, "Progress recorded successfully", recordedProgress));
    }
    
    @GetMapping("/lesson/{lessonId}")
    @Operation(summary = "Get progress for a lesson")
    public ResponseEntity<Progress> getProgress(@PathVariable Long lessonId, @RequestParam Long userId) {
        Progress progress = progressService.getProgress(userId, lessonId);
        return ResponseEntity.ok(progress);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all progress for a user")
    public ResponseEntity<List<Progress>> getUserProgress(@PathVariable Long userId) {
        List<Progress> progressList = progressService.getUserProgress(userId);
        return ResponseEntity.ok(progressList);
    }
}