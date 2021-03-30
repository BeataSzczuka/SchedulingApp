package com.example.springapp.api.mapper;

import com.example.springapp.api.model.StudentGroupDTO;
import com.example.springapp.model.StudentGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentGroupMapper {

    StudentGroupMapper INSTANCE = Mappers.getMapper(StudentGroupMapper.class);
    StudentGroupDTO studentGroupToStudentGroupDTO(StudentGroup studentGroup);
    StudentGroup studentGroupDTOToStudentGroup(StudentGroupDTO studentGroupDTO);
}
