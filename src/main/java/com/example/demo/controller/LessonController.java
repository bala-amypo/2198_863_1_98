package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.MicroLesson;
import com.example.demo.service.LessonService;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public MicroLesson createLesson(@RequestBody MicroLesson lesson) {
        return lessonService.createLesson(lesson);
    }

    @GetMapping("/difficulty/{level}")
    public List<MicroLesson> getLessonsByDifficulty(@PathVariable String level) {
        return lessonService.getLessonsByDifficulty(level);
    }
}
