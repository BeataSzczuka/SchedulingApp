package com.example.springapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", room='" + room +
                ", studentGroup=" + studentGroup +
                ", course=" + course +
                ", teachers=" + teachers +
                '}';
    }
}
