package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MicroLesson;
import com.example.demo.entity.Course;

public interface MicroLessonRepository extends JpaRepository<MicroLesson, Long> {

    List<MicroLesson> findByCourse(Course course);

    List<MicroLesson> findByDifficulty(String difficulty);
}
