package com.example.springapp.api.controller;

import com.example.springapp.api.model.CourseDTO;
import com.example.springapp.api.model.CourseListDTO;
import com.example.springapp.model.Course;
import com.example.springapp.repositories.CourseRepository;
import com.example.springapp.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("all")
    public ResponseEntity<CourseListDTO> getAllCourses(){
        return new ResponseEntity<CourseListDTO>(
                new CourseListDTO(courseService.getAllCourses()), HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }


    @GetMapping("findByName")
    @ResponseStatus(HttpStatus.OK)
    public CourseListDTO getCourseByName(@RequestParam String name){
        return new CourseListDTO(courseService.getCourseByName(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO createNewCourse(@RequestBody CourseDTO courseDTO){
        return courseService.createNewCourse(courseDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO){
        return courseService.updateCourse(id, courseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){

        courseService.deleteCourseById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
