package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/course/{courseId}")
    public MicroLesson add(@PathVariable Long courseId, @RequestBody MicroLesson lesson) {
        return lessonService.addLesson(courseId, lesson);
    }

    @GetMapping("/search")
    public List<MicroLesson> search(
            @RequestParam String tags,
            @RequestParam String difficulty,
            @RequestParam String contentType) {
        return lessonService.findLessonsByFilters(tags, difficulty, contentType);
    }
}
