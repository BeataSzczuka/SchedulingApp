package com.example.springapp.repositories;

import com.example.springapp.model.StudentGroup;
import com.example.springapp.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentGroupRepository extends CrudRepository<StudentGroup, Long> {
    Optional<StudentGroup> getFirstBySemesterAndGroupNameAndProgram(int semester, String groupName, String program);
    StudentGroup getById(Long id);
}
