package com.example.springapp.repositories;

import com.example.springapp.model.Classes;
import org.springframework.data.repository.CrudRepository;

public interface ClassesRepository extends CrudRepository<Classes, Long> {
}
