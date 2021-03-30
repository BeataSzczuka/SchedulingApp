package com.example.springapp.services;

import com.example.springapp.api.model.StudentGroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentGroupService {
    List<StudentGroupDTO> getAllStudentGroups();
    StudentGroupDTO getStudentGroupById(Long id);
    List<StudentGroupDTO> getStudentGroupBySemesterAndProgram(int semester, String program);
    List<StudentGroupDTO> getStudentGroupByName(String name);
    StudentGroupDTO createNewStudentGroup(StudentGroupDTO studentGroupDTO);
    StudentGroupDTO updateStudentGroup(Long id, StudentGroupDTO studentGroupDTO);
    void deleteStudentGroupById(Long id);
}
