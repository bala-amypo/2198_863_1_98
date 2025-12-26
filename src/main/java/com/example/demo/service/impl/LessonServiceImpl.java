package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl {

    private final MicroLessonRepository microLessonRepository;
    private final CourseRepository courseRepository;

    public LessonServiceImpl(MicroLessonRepository microLessonRepository,
                             CourseRepository courseRepository) {
        this.microLessonRepository = microLessonRepository;
        this.courseRepository = courseRepository;
    }

    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {

        if (courseId == null || lesson == null) {
            throw new RuntimeException("Invalid input");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        lesson.setCourse(course);
        return microLessonRepository.save(lesson);
    }

    public MicroLesson updateLesson(Long lessonId, MicroLesson update) {

        MicroLesson existing = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        if (update.getTitle() != null) {
            existing.setTitle(update.getTitle());
        }
        if (update.getDifficulty() != null) {
            existing.setDifficulty(update.getDifficulty());
        }
        if (update.getContentType() != null) {
            existing.setContentType(update.getContentType());
        }

        return microLessonRepository.save(existing);
    }

    public List<MicroLesson> findLessonsByFilters(
            String tags,
            String difficulty,
            String contentType
    ) {
        return microLessonRepository.findByFilters(tags, difficulty, contentType);
    }

    public MicroLesson getLesson(Long id) {
        return microLessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }
}
