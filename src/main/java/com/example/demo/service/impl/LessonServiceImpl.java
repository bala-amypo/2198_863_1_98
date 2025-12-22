package com.example.demo.service.impl;

import com.example.demo.entity.Lesson;
import com.example.demo.repository.LessonRepository;
import com.example.demo.service.LessonService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repo;

    public LessonServiceImpl(LessonRepository repo) {
        this.repo = repo;
    }

    public Lesson saveLesson(Lesson lesson) {
        return repo.save(lesson);
    }

    public List<Lesson> getAllLessons() {
        return repo.findAll();
    }

    public Lesson getLessonById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }
}
