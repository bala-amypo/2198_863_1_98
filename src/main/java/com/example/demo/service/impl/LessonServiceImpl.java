package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Course;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    
    @Autowired
    private MicroLessonRepository microLessonRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Override
    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        
        if (lesson.getDurationMinutes() == null || lesson.getDurationMinutes() <= 0 || lesson.getDurationMinutes() > 15) {
            throw new IllegalArgumentException("Duration must be between 1 and 15 minutes");
        }
        
        if (lesson.getContentType() == null || lesson.getDifficulty() == null) {
            throw new IllegalArgumentException("Content type and difficulty are required");
        }
        
        lesson.setCourse(course);
        return microLessonRepository.save(lesson);
    }
    
    @Override
    public MicroLesson updateLesson(Long lessonId, MicroLesson lesson) {
        MicroLesson existingLesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
        
        if (lesson.getTitle() != null) {
            existingLesson.setTitle(lesson.getTitle());
        }
        if (lesson.getDurationMinutes() != null) {
            if (lesson.getDurationMinutes() <= 0 || lesson.getDurationMinutes() > 15) {
                throw new IllegalArgumentException("Duration must be between 1 and 15 minutes");
            }
            existingLesson.setDurationMinutes(lesson.getDurationMinutes());
        }
        if (lesson.getContentType() != null) {
            existingLesson.setContentType(lesson.getContentType());
        }
        if (lesson.getDifficulty() != null) {
            existingLesson.setDifficulty(lesson.getDifficulty());
        }
        if (lesson.getTags() != null) {
            existingLesson.setTags(lesson.getTags());
        }
        if (lesson.getPublishDate() != null) {
            existingLesson.setPublishDate(lesson.getPublishDate());
        }
        
        return microLessonRepository.save(existingLesson);
    }
    
    @Override
    public List<MicroLesson> findLessonsByFilters(String tags, String difficulty, String contentType) {
        return microLessonRepository.findByFilters(tags, difficulty, contentType);
    }
    
    @Override
    public MicroLesson getLesson(Long lessonId) {
        return microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
    }
}