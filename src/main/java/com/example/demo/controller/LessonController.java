package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lessons")
@Tag(name = "Lessons", description = "Lesson management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class LessonController {
    
    @Autowired
    private LessonService lessonService;
    
    @PostMapping("/course/{courseId}")
    @Operation(summary = "Add a lesson to a course")
    public ResponseEntity<ApiResponse> addLesson(@PathVariable Long courseId, @RequestBody MicroLesson lesson) {
        MicroLesson createdLesson = lessonService.addLesson(courseId, lesson);
        return ResponseEntity.ok(new ApiResponse(true, "Lesson added successfully", createdLesson));
    }
    
    @PutMapping("/{lessonId}")
    @Operation(summary = "Update a lesson")
    public ResponseEntity<ApiResponse> updateLesson(@PathVariable Long lessonId, @RequestBody MicroLesson lesson) {
        MicroLesson updatedLesson = lessonService.updateLesson(lessonId, lesson);
        return ResponseEntity.ok(new ApiResponse(true, "Lesson updated successfully", updatedLesson));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search lessons by filters")
    public ResponseEntity<List<MicroLesson>> searchLessons(
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) String contentType) {
        List<MicroLesson> lessons = lessonService.findLessonsByFilters(tags, difficulty, contentType);
        return ResponseEntity.ok(lessons);
    }
    
    @GetMapping("/{lessonId}")
    @Operation(summary = "Get lesson by ID")
    public ResponseEntity<MicroLesson> getLesson(@PathVariable Long lessonId) {
        MicroLesson lesson = lessonService.getLesson(lessonId);
        return ResponseEntity.ok(lesson);
    }
}