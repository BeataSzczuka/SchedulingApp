package com.example.springapp.repositories;

import com.example.springapp.model.Course;
import com.example.springapp.model.StudentGroup;
import com.example.springapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
    List<StudentGroup> getByGroupName(String groupName);
    List<StudentGroup> getBySemesterAndProgram(int semester, String program);
    StudentGroup getById(Long id);
}
