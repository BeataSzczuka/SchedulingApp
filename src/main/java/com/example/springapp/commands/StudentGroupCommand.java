package com.example.springapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentGroupCommand {
    private Long id;
    private int semester;
    private String program;
    private String groupName;

}
