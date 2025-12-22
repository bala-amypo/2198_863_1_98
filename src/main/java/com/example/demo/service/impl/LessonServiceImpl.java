package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.model.Course;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<MicroLesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public MicroLesson getLesson(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
    }

    @Override
    public MicroLesson createLesson(MicroLesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public MicroLesson updateLesson(Long id, MicroLesson lessonDetails) {
        MicroLesson lesson = getLesson(id);
        lesson.setTitle(lessonDetails.getTitle());
        lesson.setContentType(lessonDetails.getContentType());
        lesson.setDurationMinutes(lessonDetails.getDurationMinutes());
        lesson.setCourse(lessonDetails.getCourse());
        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        MicroLesson lesson = getLesson(id);
        lessonRepository.delete(lesson);
    }
}
