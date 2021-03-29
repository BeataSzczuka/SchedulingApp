package com.example.springapp.controllers;

import com.example.springapp.repositories.ClassesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClassesController {

    private ClassesRepository classesRepository;

    public ClassesController(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    @RequestMapping("/classes")
    public String getClasses(Model model) {

        model.addAttribute("classes", classesRepository.findAll());

        return "classes";
    }
}
