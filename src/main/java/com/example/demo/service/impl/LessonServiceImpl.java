package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.model.Course;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private MicroLessonRepository lessonRepository; // corrected

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public MicroLesson createLesson(MicroLesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public MicroLesson updateLesson(Long lessonId, MicroLesson lessonDetails) {
        MicroLesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));

        lesson.setTitle(lessonDetails.getTitle());
        lesson.setDurationMinutes(lessonDetails.getDurationMinutes());
        lesson.setContentType(lessonDetails.getContentType());
        lesson.setDifficulty(lessonDetails.getDifficulty());
        lesson.setTags(lessonDetails.getTags());
        lesson.setPublishDate(lessonDetails.getPublishDate());
        lesson.setCourse(lessonDetails.getCourse());

        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(Long lessonId) {
        MicroLesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));
        lessonRepository.delete(lesson);
    }

    @Override
    public List<MicroLesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public MicroLesson getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));
    }
}
