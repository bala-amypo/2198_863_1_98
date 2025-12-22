package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public Course createCourse(Course course, Long instructorId) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        if (courseRepository.existsByTitleAndInstructorId(course.getTitle(), instructorId)) {
            throw new IllegalArgumentException("Course title already exists for instructor");
        }

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> listCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
}
