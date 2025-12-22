package com.example.demo.service;

import com.example.demo.entity.Lesson;
import java.util.List;

public interface LessonService {
    Lesson saveLesson(Lesson lesson);
    List<Lesson> getAllLessons();
    Lesson getLessonById(Long id);
}
