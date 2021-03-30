package com.example.springapp.api.mapper;

import com.example.springapp.api.model.CourseDTO;
import com.example.springapp.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseDTO courseToCourseDTO(Course course);
    Course courseDTOToCourse(CourseDTO courseDTO);
}
