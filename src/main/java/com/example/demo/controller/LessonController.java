package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/course/{courseId}")
    public MicroLesson addLesson(@PathVariable Long courseId, @RequestBody MicroLesson lesson) {
        return lessonService.addLesson(courseId, lesson);
    }

    @PutMapping("/{lessonId}")
    public MicroLesson updateLesson(@PathVariable Long lessonId, @RequestBody MicroLesson lesson) {
        return lessonService.updateLesson(lessonId, lesson);
    }

    @GetMapping("/search")
    public List<MicroLesson> searchLessons(
            @RequestParam String tags,
            @RequestParam String difficulty,
            @RequestParam String contentType) {
        return lessonService.findLessonsByFilters(tags, difficulty, contentType);
    }

    @GetMapping("/{lessonId}")
    public MicroLesson getLesson(@PathVariable Long lessonId) {
        return lessonService.getLesson(lessonId);
    }
}
