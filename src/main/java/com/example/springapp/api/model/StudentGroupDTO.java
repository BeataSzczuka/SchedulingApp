package com.example.springapp.api.model;

import lombok.Data;

@Data
public class StudentGroupDTO {
    Long id;
    int semester;
    String program;
    String groupName;
}
