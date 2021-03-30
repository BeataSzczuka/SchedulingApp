package com.example.springapp.converters;
import com.example.springapp.commands.ClassesCommand;
import com.example.springapp.model.Classes;
import com.example.springapp.model.StudentGroup;
import com.example.springapp.repositories.StudentGroupRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ClassesCommandToClassesConverter implements Converter<ClassesCommand, Classes> {

    private StudentGroupRepository studentGroupRepository;

    public ClassesCommandToClassesConverter(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Classes convert(ClassesCommand source) {
        if (source == null) {
            return null;
        }
        final Classes classes = new Classes();

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm",  Locale.getDefault());
        classes.setEndTime( LocalDateTime.parse(source.getEndTime()));
        classes.setStartTime(LocalDateTime.parse(source.getStartTime()));
        classes.setRoom(source.getRoom());

        StudentGroup studentGroup = studentGroupRepository.getById(source.getStudentGroupId());
        classes.setStudentGroup(studentGroup);
        return classes;
    }
}
