package com.example.springapp.controllers;

import com.example.springapp.commands.StudentGroupCommand;
import com.example.springapp.commands.StudentGroupCommand;
import com.example.springapp.converters.StudentGroupCommandToStudentGroupConverter;
import com.example.springapp.model.StudentGroup;
import com.example.springapp.model.StudentGroup;
import com.example.springapp.repositories.ClassesRepository;
import com.example.springapp.repositories.StudentGroupRepository;
import com.example.springapp.repositories.StudentGroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class StudentGroupController {

    private StudentGroupRepository studentGroupRepository;
    private StudentGroupCommandToStudentGroupConverter studentGroupCommandToStudentGroup;

    public StudentGroupController(StudentGroupRepository studentGroupRepository, StudentGroupCommandToStudentGroupConverter studentGroupCommandToStudentGroup) {
        this.studentGroupRepository = studentGroupRepository;
        this.studentGroupCommandToStudentGroup = studentGroupCommandToStudentGroup;
    }

    @RequestMapping(value = {"/studentGroups", "/studentGroup/list"})
    public String getStudentGroups(Model model) {

        model.addAttribute("studentGroups", studentGroupRepository.findAll());

        return "studentGroup/list";
    }

    @RequestMapping("/studentGroup/{id}/show")
    public String getStudentGroupDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("studentGroup", studentGroupRepository.findById(id).get());
        return "studentGroup/show";
    }

    @RequestMapping("/studentGroup/{id}/delete")
    public String deleteStudentGroup(@PathVariable("id") Long id) {
        try {
            studentGroupRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "redirect:/studentGroups";
    }

    @GetMapping
    @RequestMapping("/studentGroup/new")
    public String newStudentGroup(Model model){
        model.addAttribute("studentGroup", new StudentGroupCommand());
        return "studentGroup/addedit";
    }

    @GetMapping
    @RequestMapping("/studentGroup/{id}/update")
    public String updateCourse(Model model, @PathVariable("id") Long id){
        try{
            model.addAttribute("studentGroup", studentGroupRepository.findById(id).get());
            return "studentGroup/addedit";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "redirect:/studentGroups";
        }
    }

    @PostMapping("studentGroup")
    public String saveOrUpdate(@ModelAttribute StudentGroupCommand command){
        try{
            StudentGroup detachedStudentGroup = studentGroupCommandToStudentGroup.convert(command);
            if (command.getId() != null) {
                detachedStudentGroup.setId(command.getId());
            }
            StudentGroup savedStudentGroup = studentGroupRepository.save(detachedStudentGroup);
            return "redirect:/studentGroup/" + savedStudentGroup.getId() + "/show";
        } catch (Exception e) {
            System.out.println(e.toString());
                return "redirect:/studentGroups";
        }
    }

}
