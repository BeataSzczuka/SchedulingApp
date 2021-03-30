package com.example.springapp.controllers;

import com.example.springapp.commands.TeacherCommand;
import com.example.springapp.converters.TeacherCommandToTeacherConverter;
import com.example.springapp.model.Teacher;
import com.example.springapp.model.Teacher;
import com.example.springapp.repositories.ClassesRepository;
import com.example.springapp.repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TeacherController {

    private TeacherRepository teacherRepository;
    private ClassesRepository classesRepository;
    private TeacherCommandToTeacherConverter teacherCommandToTeacher;

    public TeacherController(TeacherRepository teacherRepository, ClassesRepository classesRepository, TeacherCommandToTeacherConverter teacherCommandToTeacher) {
        this.teacherRepository = teacherRepository;
        this.classesRepository = classesRepository;
        this.teacherCommandToTeacher = teacherCommandToTeacher;
    }

    @RequestMapping(value = {"/teachers", "/teacher/list"})
    public String getTeachers(Model model) {

        model.addAttribute("teachers", teacherRepository.findAll());

        return "teacher/list";
    }

    @RequestMapping("/teacher/{id}/classes")
    public String getTeacherClasses(Model model, @PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isPresent()) {
            model.addAttribute("classes", classesRepository.getAllByTeachersIsContaining(teacher.get()));
        } else {
            model.addAttribute("classes", new ArrayList<>());
        }

        return "classes/list";
    }

    @RequestMapping("/teacher/{id}/show")
    public String getTeacherDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("teacher", teacherRepository.findById(id).get());
        return "teacher/show";
    }

    @RequestMapping("/teacher/{id}/delete")
    public String deleteTeacher(@PathVariable("id") Long id) {
        try{
            teacherRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "redirect:/teachers";
    }

    @GetMapping
    @RequestMapping("/teacher/new")
    public String newTeacher(Model model){
        model.addAttribute("teacher", new TeacherCommand());
        return "teacher/addedit";
    }

    @GetMapping
    @RequestMapping("/teacher/{id}/update")
    public String updateCourse(Model model, @PathVariable("id") Long id){
        try{
            model.addAttribute("teacher", teacherRepository.findById(id).get());
            return "teacher/addedit";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "redirect:/teachers";
        }
    }

    @PostMapping("teacher")
    public String saveOrUpdate(@ModelAttribute TeacherCommand command){
        try{
            Teacher detachedTeacher = teacherCommandToTeacher.convert(command);
            if (command.getId() != null) {
                detachedTeacher.setId(command.getId());
            }
            Teacher savedTeacher = teacherRepository.save(detachedTeacher);
            return "redirect:/teacher/" + savedTeacher.getId() + "/show";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "redirect:/teachers";
        }
    }

}
