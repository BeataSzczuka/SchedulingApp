package com.example.springapp.commands;

import com.example.springapp.model.StudentGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ClassesCommand {
    private Long id;
    private String startTime;
    private String endTime;
    private String room;
    private Long studentGroupId;
}
