package com.example.springapp.api.mapper;

import com.example.springapp.api.model.CourseDTO;
import com.example.springapp.model.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {

    CourseMapper courseMapper = CourseMapper.INSTANCE;
    @Test
    void courseToCourseDTO() {
        //given
        Course course = new Course();
        course.setId(1L);
        course.setName("Course");
        //when
        CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);
        //then
        assertEquals(Long.valueOf(1L), courseDTO.getId());
        assertEquals("Course", courseDTO.getName());

    }
}