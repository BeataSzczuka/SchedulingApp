package com.example.springapp.controllers;

import com.example.springapp.commands.ClassesCommand;
import com.example.springapp.converters.ClassesCommandToClassesConverter;
import com.example.springapp.converters.ClassesToClassesCommandConverter;
import com.example.springapp.model.Classes;
import com.example.springapp.model.Teacher;
import com.example.springapp.repositories.ClassesRepository;
import com.example.springapp.repositories.ClassesRepository;
import com.example.springapp.repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ClassesController {
    private ClassesRepository classesRepository;
    private TeacherRepository teacherRepository;
    private ClassesCommandToClassesConverter classesCommandToClasses;
    private ClassesToClassesCommandConverter classesToClassesCommand;

    public ClassesController(ClassesRepository classesRepository, ClassesCommandToClassesConverter classesCommandToClasses, ClassesToClassesCommandConverter classesToClassesCommand) {
        this.classesRepository = classesRepository;
        this.classesCommandToClasses = classesCommandToClasses;
        this.classesToClassesCommand = classesToClassesCommand;
    }

    @RequestMapping(value = {"/classes", "/classes/list"})
    public String getClasses(Model model) {

        model.addAttribute("classes", classesRepository.findAll());

        return "classes/list";
    }

    @RequestMapping("/classes/{id}/teachers")
    public String getClassesTeachers(Model model, @PathVariable("id") Long id) {
        Optional<Classes> classes = classesRepository.findById(id);

        if (classes.isPresent()) {
            model.addAttribute("teachers", teacherRepository.getAllByClassesIsContaining(classes.get()));
        } else {
            model.addAttribute("teachers", new ArrayList<>());
        }

        return "teachers/list";
    }


    @RequestMapping("/classes/{id}/show")
    public String getClassesDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("classes", classesRepository.findById(id).get());
        return "classes/show";
    }

    @RequestMapping("/classes/{id}/delete")
    public String deleteClasses(@PathVariable("id") Long id) {
        try{
            classesRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "redirect:/classes";
    }

    @GetMapping
    @RequestMapping("/classes/new")
    public String newClasses(Model model){
        model.addAttribute("classes", new ClassesCommand());
        return "classes/addedit";
    }

    @GetMapping
    @RequestMapping("/classes/{id}/update")
    public String updateClasses(Model model, @PathVariable("id") Long id){
        try{
            Classes classes = classesRepository.findById(id).get();

            model.addAttribute("classes", classesToClassesCommand.convert(classes));
            return "classes/addedit";
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "redirect:/classes";
    }

    @PostMapping("classes")
    public String saveOrUpdate(@ModelAttribute ClassesCommand command){
        Classes detachedClasses = classesCommandToClasses.convert(command);
        if (command.getId() != null) {
            detachedClasses.setId(command.getId());
        }
        Classes savedClasses = classesRepository.save(detachedClasses);
        return "redirect:/classes/" + savedClasses.getId() + "/show";
    }

}
