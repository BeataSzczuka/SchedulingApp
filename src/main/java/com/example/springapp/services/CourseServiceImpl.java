package com.example.springapp.services;

import com.example.springapp.api.mapper.CourseMapper;
import com.example.springapp.api.model.CourseDTO;
import com.example.springapp.model.Course;
import com.example.springapp.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import javax.crypto.spec.PSource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    CourseRepository courseRepository;
    CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository
                .findAll()
                .stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        return courseMapper.courseToCourseDTO(courseRepository.findById(id).get());
    }

    @Override
    public List<CourseDTO> getCourseByName(String name) {
        return courseRepository
                .getByName(name)
                .stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO createNewCourse(CourseDTO courseDTO) {
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        Course savedCourse = courseRepository.save(course);

        return courseMapper.courseToCourseDTO(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        course.setId(id);
        Course savedCourse = courseRepository.save(course);

        return courseMapper.courseToCourseDTO(savedCourse);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}
