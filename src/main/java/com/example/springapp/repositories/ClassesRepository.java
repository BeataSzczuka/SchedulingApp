package com.example.springapp.repositories;

import com.example.springapp.model.Classes;
import com.example.springapp.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassesRepository extends CrudRepository<Classes, Long> {
    List<Classes> getAllByTeachersIsContaining(Teacher teacher);
}
