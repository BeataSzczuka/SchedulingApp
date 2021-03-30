package com.example.springapp.services;

import com.example.springapp.api.model.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO getCourseById(Long id);
    List<CourseDTO> getCourseByName(String name);
    CourseDTO createNewCourse(CourseDTO courseDTO);
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    void deleteCourseById(Long id);
}
