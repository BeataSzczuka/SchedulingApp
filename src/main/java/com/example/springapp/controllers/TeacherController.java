package com.example.springapp.controllers;

import com.example.springapp.commands.TeacherCommand;
import com.example.springapp.converters.TeacherCommandToTeacherConverter;
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
    public String getTeacherSongs(Model model, @PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isPresent()) {
            model.addAttribute("classes", classesRepository.getAllByTeachersIsContaining(teacher.get()));
            model.addAttribute("filter", "teacher: " + teacher.get().getFirstName() + " " + teacher.get().getLastName());
        } else {
            model.addAttribute("classes", new ArrayList<>());
            model.addAttribute("filter", "teacher for this id doesn't exist");
        }

        return "song/list";
    }

    @RequestMapping("/teacher/{id}/show")
    public String getTeacherDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("teacher", teacherRepository.findById(id).get());
        return "teacher/show";
    }

    @RequestMapping("/teacher/{id}/delete")
    public String deleteTeacher(@PathVariable("id") Long id) {
        teacherRepository.deleteById(id);
        return "redirect:/teachers";
    }

    @GetMapping
    @RequestMapping("/teacher/new")
    public String newTeacher(Model model){
        model.addAttribute("teacher", new TeacherCommand());
        return "teacher/addedit";
    }

    @PostMapping("teacher")
    public String saveOrUpdate(@ModelAttribute TeacherCommand command){
        Optional<Teacher> teacherOptional = teacherRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!teacherOptional.isPresent()) {
            Teacher detachedTeacher = teacherCommandToTeacher.convert(command);
            Teacher savedTeacher = teacherRepository.save(detachedTeacher);
            return "redirect:/teacher/" + savedTeacher.getId() + "/show";
        } else {
            System.out.println("Sorry, there's such teacher in db");
            return "redirect:/teacher/" + teacherOptional.get().getId() + "/show";
        }
    }

}
