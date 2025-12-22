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

    @PostMapping
    public MicroLesson createLesson(@RequestBody MicroLesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @GetMapping("/difficulty/{level}")
    public List<MicroLesson> getByDifficulty(@PathVariable String level) {
        return lessonService.getLessonsByDifficulty(level);
    }
}
