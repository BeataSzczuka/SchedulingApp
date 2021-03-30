package com.example.springapp.services;

import com.example.springapp.api.mapper.StudentGroupMapper;
import com.example.springapp.api.model.StudentGroupDTO;
import com.example.springapp.model.StudentGroup;
import com.example.springapp.repositories.StudentGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentGroupServiceImpl implements StudentGroupService{

    StudentGroupRepository studentGroupRepository;
    StudentGroupMapper studentGroupMapper;

    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository, StudentGroupMapper studentGroupMapper) {
        this.studentGroupRepository = studentGroupRepository;
        this.studentGroupMapper = studentGroupMapper;
    }

    @Override
    public List<StudentGroupDTO> getAllStudentGroups() {
        return studentGroupRepository
                .findAll()
                .stream()
                .map(studentGroupMapper::studentGroupToStudentGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentGroupDTO getStudentGroupById(Long id) {
        return studentGroupMapper.studentGroupToStudentGroupDTO(studentGroupRepository.findById(id).get());
    }

    @Override
    public List<StudentGroupDTO> getStudentGroupBySemesterAndProgram(int semester, String program) {
        return studentGroupRepository
                .getBySemesterAndProgram(semester, program)
                .stream()
                .map(studentGroupMapper::studentGroupToStudentGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentGroupDTO> getStudentGroupByName(String name) {
        return studentGroupRepository
                .getByGroupName(name)
                .stream()
                .map(studentGroupMapper::studentGroupToStudentGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentGroupDTO createNewStudentGroup(StudentGroupDTO studentGroupDTO) {
        StudentGroup studentGroup = studentGroupMapper.studentGroupDTOToStudentGroup(studentGroupDTO);
        StudentGroup savedStudentGroup = studentGroupRepository.save(studentGroup);

        return studentGroupMapper.studentGroupToStudentGroupDTO(savedStudentGroup);
    }

    @Override
    public StudentGroupDTO updateStudentGroup(Long id, StudentGroupDTO studentGroupDTO) {
        StudentGroup studentGroup = studentGroupMapper.studentGroupDTOToStudentGroup(studentGroupDTO);
        studentGroup.setId(id);
        StudentGroup savedStudentGroup = studentGroupRepository.save(studentGroup);

        return studentGroupMapper.studentGroupToStudentGroupDTO(savedStudentGroup);
    }

    @Override
    public void deleteStudentGroupById(Long id) {
        studentGroupRepository.deleteById(id);
    }
}
