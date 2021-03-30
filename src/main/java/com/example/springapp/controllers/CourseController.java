package com.example.springapp.controllers;

import com.example.springapp.commands.CourseCommand;
import com.example.springapp.converters.CourseCommandToCourseConverter;
import com.example.springapp.model.Course;
import com.example.springapp.repositories.ClassesRepository;
import com.example.springapp.repositories.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CourseController {

    private CourseRepository courseRepository;
    private CourseCommandToCourseConverter courseCommandToCourse;

    public CourseController(CourseRepository courseRepository, CourseCommandToCourseConverter courseCommandToCourse) {
        this.courseRepository = courseRepository;
        this.courseCommandToCourse = courseCommandToCourse;
    }

    @RequestMapping(value = {"/courses", "/course/list"})
    public String getCourses(Model model) {

        model.addAttribute("courses", courseRepository.findAll());

        return "course/list";
    }


    @RequestMapping("/course/{id}/show")
    public String getCourseDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseRepository.findById(id).get());
        return "course/show";
    }

    @RequestMapping("/course/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id) {
        try{
            courseRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "redirect:/courses";
    }

    @GetMapping
    @RequestMapping("/course/new")
    public String newCourse(Model model){
        model.addAttribute("course", new CourseCommand());
        return "course/addedit";
    }

    @GetMapping
    @RequestMapping("/course/{id}/update")
    public String updateCourse(Model model, @PathVariable("id") Long id){
        try{
            model.addAttribute("course", courseRepository.findById(id).get());
            return "course/addedit";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "redirect:/courses";
        }
    }

    @PostMapping("course")
    public String saveOrUpdate(@ModelAttribute CourseCommand command){
        Course detachedCourse = courseCommandToCourse.convert(command);
        if (command.getId() != null) {
            detachedCourse.setId(command.getId());
        }
        Course savedCourse = courseRepository.save(detachedCourse);
        return "redirect:/course/" + savedCourse.getId() + "/show";
    }

}
