package com.example.demo.service;

import com.example.demo.model.Course;
import java.util.List;

public interface CourseService {

    Course createCourse(Course course);

    Course updateCourse(Long courseId, Course course);

    Course getCourse(Long courseId);
}

