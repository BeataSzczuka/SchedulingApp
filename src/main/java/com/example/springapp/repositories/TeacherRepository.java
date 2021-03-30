package com.example.springapp.repositories;

import com.example.springapp.model.Classes;
import com.example.springapp.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> getAllByClassesIsContaining(Classes classes);

}
