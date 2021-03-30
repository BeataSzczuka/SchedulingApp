package com.example.springapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseListDTO {
    private List<CourseDTO> courseList;
}
