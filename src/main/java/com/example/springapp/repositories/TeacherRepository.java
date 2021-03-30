package com.example.springapp.repositories;

import com.example.springapp.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Optional<Teacher> getFirstByFirstNameAndLastName(String firstName, String lastName);
    Optional<Teacher> getFirstByFirstName(String firstName);
}
