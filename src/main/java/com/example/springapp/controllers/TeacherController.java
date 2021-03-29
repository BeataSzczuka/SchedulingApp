package com.example.springapp.controllers;

import com.example.springapp.repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherController {

    private TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @RequestMapping("/teachers")
    public String getTeachers(Model model) {

        model.addAttribute("teachers", teacherRepository.findAll());

        return "teachers";
    }
}
