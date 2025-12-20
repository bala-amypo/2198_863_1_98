package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MicroLesson;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

    private final MicroLessonRepository lessonRepository;

    public LessonServiceImpl(MicroLessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public MicroLesson createLesson(MicroLesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public List<MicroLesson> getLessonsByDifficulty(String difficulty) {
        return lessonRepository.findByDifficulty(difficulty);
    }
}
