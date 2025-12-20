package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.MicroLesson;

public interface LessonService {

    MicroLesson createLesson(MicroLesson lesson);

    List<MicroLesson> getLessonsByDifficulty(String difficulty);
}
