package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
@Tag(name = "Courses", description = "Course management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @PostMapping
    @Operation(summary = "Create a new course")
    public ResponseEntity<ApiResponse> createCourse(@RequestBody Course course, @RequestParam Long instructorId) {
        Course createdCourse = courseService.createCourse(course, instructorId);
        return ResponseEntity.ok(new ApiResponse(true, "Course created successfully", createdCourse));
    }
    
    @PutMapping("/{courseId}")
    @Operation(summary = "Update an existing course")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(courseId, course);
        return ResponseEntity.ok(new ApiResponse(true, "Course updated successfully", updatedCourse));
    }
    
    @GetMapping("/instructor/{instructorId}")
    @Operation(summary = "Get courses by instructor")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Long instructorId) {
        List<Course> courses = courseService.listCoursesByInstructor(instructorId);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/{courseId}")
    @Operation(summary = "Get course by ID")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
        Course course = courseService.getCourse(courseId);
        return ResponseEntity.ok(course);
    }
}