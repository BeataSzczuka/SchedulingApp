package com.example.springapp.repositories;

import com.example.springapp.model.StudentGroup;
import org.springframework.data.repository.CrudRepository;

public interface StudentGroupRepository extends CrudRepository<StudentGroup, Long> {
}
