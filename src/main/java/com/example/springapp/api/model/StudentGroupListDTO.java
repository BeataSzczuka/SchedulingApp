package com.example.springapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentGroupListDTO {
    private List<StudentGroupDTO> studentGroupList;
}
