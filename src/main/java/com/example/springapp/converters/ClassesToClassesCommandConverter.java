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

@Component
public class ClassesToClassesCommandConverter implements Converter<Classes, ClassesCommand> {

    private StudentGroupRepository studentGroupRepository;

    public ClassesToClassesCommandConverter(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public ClassesCommand convert(Classes source) {
        if (source == null) {
            return null;
        }
        final ClassesCommand classesCommand = new ClassesCommand();

        classesCommand.setEndTime( source.getEndTime().toString());
        classesCommand.setStartTime(source.getStartTime().toString());
        classesCommand.setRoom(source.getRoom());

        classesCommand.setStudentGroupId(source.getStudentGroup().getId());
        return classesCommand;
    }
}
