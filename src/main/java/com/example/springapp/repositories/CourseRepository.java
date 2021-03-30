package com.example.springapp.repositories;

import com.example.springapp.model.Course;
import com.example.springapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> getByName(String name);
    Optional<Course> getFirstByName(String name);
}
