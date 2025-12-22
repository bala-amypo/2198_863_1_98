package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(Course course, Long instructorId) {
        User instructor = userRepository.findById(instructorId).orElse(null);
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long courseId, Course course) {
        course.setId(courseId);
        return courseRepository.save(course);
    }

    public List<Course> listCoursesByInstructor(Long instructorId) {
        return courseRepository.findAll();
    }

    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
}
