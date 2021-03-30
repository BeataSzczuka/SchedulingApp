package com.example.springapp.converters;
import com.example.springapp.commands.StudentGroupCommand;
import com.example.springapp.model.StudentGroup;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StudentGroupCommandToStudentGroupConverter implements Converter<StudentGroupCommand, StudentGroup> {
    @Synchronized
    @Nullable
    @Override
    public StudentGroup convert(StudentGroupCommand source) {
        if (source == null) {
            return null;
        }
        final StudentGroup studentGroup = new StudentGroup();
        studentGroup.setGroupName(source.getGroupName());
        studentGroup.setSemester(source.getSemester());
        studentGroup.setProgram(source.getProgram());
        return studentGroup;
    }
}
