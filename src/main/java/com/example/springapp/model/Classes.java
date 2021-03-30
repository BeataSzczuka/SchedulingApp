package com.example.springapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String room;

    @ManyToOne
    private StudentGroup studentGroup;

    @ManyToOne
    private Course course;

    @ManyToMany
    private Set<Teacher> teachers = new HashSet<>();

    public Classes() {
    }

    public Classes(LocalDateTime startTime, LocalDateTime endTime, String room, StudentGroup studentGroup, Course course) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.studentGroup = studentGroup;
        this.course = course;
    }
}
