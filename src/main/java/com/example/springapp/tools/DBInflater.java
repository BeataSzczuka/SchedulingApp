package com.example.springapp.tools;

import com.example.springapp.model.Classes;
import com.example.springapp.model.Course;
import com.example.springapp.model.StudentGroup;
import com.example.springapp.model.Teacher;
import com.example.springapp.repositories.ClassesRepository;
import com.example.springapp.repositories.CourseRepository;
import com.example.springapp.repositories.StudentGroupRepository;
import com.example.springapp.repositories.TeacherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    private TeacherRepository teacherRepository;
    private ClassesRepository classesRepository;
    private CourseRepository courseRepository;
    private StudentGroupRepository studentGroupRepository;

    public DBInflater(TeacherRepository teacherRepository, ClassesRepository classesRepository, CourseRepository courseRepository, StudentGroupRepository studentGroupRepository) {
        this.teacherRepository = teacherRepository;
        this.classesRepository = classesRepository;
        this.courseRepository = courseRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Teacher teacher1 = new Teacher("John", "Cash");
        Teacher teacher2 = new Teacher("Alan", "Watt");

        StudentGroup group1 = new StudentGroup(1, "Computer science", "CS 1/A");
        StudentGroup group2 = new StudentGroup(3, "Computer science", "CS 3/B");

        Course course1 = new Course("Computer Networks");
        Course course2 = new Course("Algorithms and Data Structures");

        LocalDateTime startTime = LocalDateTime.of(2021, 4, 8, 12, 00);
        Classes classes1 = new Classes(startTime, startTime.plusHours(2), "11", group1, course1);
        Classes classes2 = new Classes(startTime.plusDays(1), startTime.plusDays(1).plusHours(3), "11", group2, course2);

        classes1.getTeachers().add(teacher1);
        teacher1.getClasses().add(classes1);

        classes2.getTeachers().add(teacher2);
        teacher2.getClasses().add(classes2);

        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);

        courseRepository.save(course1);
        courseRepository.save(course2);
        
        studentGroupRepository.save(group1);
        studentGroupRepository.save(group2);

        classesRepository.save(classes1);
        classesRepository.save(classes2);

    }
}
