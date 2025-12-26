package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(Course course, Long instructorId) {

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        if (!"INSTRUCTOR".equals(instructor.getRole()) &&
            !"ADMIN".equals(instructor.getRole())) {
            throw new RuntimeException("Unauthorized");
        }

        if (courseRepository.existsByTitleAndInstructorId(
                course.getTitle(), instructorId)) {
            throw new RuntimeException("Duplicate course");
        }

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long courseId, Course update) {

        Course existing = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (update.getTitle() != null) {
            existing.setTitle(update.getTitle());
        }
        if (update.getDescription() != null) {
            existing.setDescription(update.getDescription());
        }

        return courseRepository.save(existing);
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
