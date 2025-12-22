package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course create(@RequestBody Course course, @RequestParam Long instructorId) {
        return courseService.createCourse(course, instructorId);
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @GetMapping("/instructor/{id}")
    public List<Course> list(@PathVariable Long id) {
        return courseService.listCoursesByInstructor(id);
    }
}
