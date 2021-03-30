package com.example.springapp.controllers;

import com.example.springapp.repositories.StudentGroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentGroupController {

    private StudentGroupRepository studentGroupRepository;

    public StudentGroupController(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @RequestMapping("/studentGroups")
    public String getStudentGroups(Model model) {

        model.addAttribute("studentGroups", studentGroupRepository.findAll());

        return "studentGroups/studentGroups";
    }
}
