package com.example.springapp.converters;
import com.example.springapp.commands.TeacherCommand;
import com.example.springapp.model.Teacher;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TeacherCommandToTeacherConverter implements Converter<TeacherCommand, Teacher> {
    @Synchronized
    @Nullable
    @Override
    public Teacher convert(TeacherCommand source) {
        if (source == null) {
            return null;
        }
        final Teacher teacher = new Teacher();
        teacher.setFirstName(source.getFirstName());
        teacher.setLastName(source.getLastName());
        return teacher;
    }
}
