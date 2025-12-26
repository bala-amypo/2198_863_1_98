package com.example.demo.repository;

import com.example.demo.model.MicroLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroLessonRepository extends JpaRepository<MicroLesson, Long> {

    List<MicroLesson> findByFilters(String tags, String difficulty, String contentType);
}
