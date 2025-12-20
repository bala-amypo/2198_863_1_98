package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Course;

public interface CourseService {

    Course createCourse(Course course);

    List<Course> getAllCourses();
}
