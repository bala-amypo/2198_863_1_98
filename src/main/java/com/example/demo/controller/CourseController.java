package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{instructorId}")
    public Course createCourse(@RequestBody Course course,
                               @PathVariable Long instructorId) {
        return courseService.createCourse(course, instructorId);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable Long courseId,
                               @RequestBody Course course) {
        return courseService.updateCourse(courseId, course);
    }

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return courseService.getCourse(courseId);
    }
}
