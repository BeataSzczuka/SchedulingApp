package com.example.springapp.api.controller;

import com.example.springapp.api.model.StudentGroupDTO;
import com.example.springapp.api.model.StudentGroupListDTO;
import com.example.springapp.services.StudentGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/studentGroup/")
public class StudentGroupRestController {

    private final StudentGroupService studentGroupService;

    public StudentGroupRestController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @GetMapping("all")
    public ResponseEntity<StudentGroupListDTO> getAllStudentGroups(){
        return new ResponseEntity<StudentGroupListDTO>(
                new StudentGroupListDTO(studentGroupService.getAllStudentGroups()), HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentGroupDTO getStudentGroupById(@PathVariable Long id){
        return studentGroupService.getStudentGroupById(id);
    }


    @GetMapping("findByName")
    @ResponseStatus(HttpStatus.OK)
    public StudentGroupListDTO getStudentGroupByName(@RequestParam String name){
        return new StudentGroupListDTO(studentGroupService.getStudentGroupByName(name));
    }

    @GetMapping("findBySemesterAndProgram")
    @ResponseStatus(HttpStatus.OK)
    public StudentGroupListDTO getStudentGroupBySemesterAndProgram(@RequestParam int semester, @RequestParam String program){
        return new StudentGroupListDTO(studentGroupService.getStudentGroupBySemesterAndProgram(semester, program));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentGroupDTO createNewStudentGroup(@RequestBody StudentGroupDTO studentGroupDTO){
        return studentGroupService.createNewStudentGroup(studentGroupDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentGroupDTO updateStudentGroup(@PathVariable Long id, @RequestBody StudentGroupDTO studentGroupDTO){
        return studentGroupService.updateStudentGroup(id, studentGroupDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudentGroup(@PathVariable Long id){

        studentGroupService.deleteStudentGroupById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
