package com.example.springapp.converters;
import com.example.springapp.commands.CourseCommand;
import com.example.springapp.model.Course;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandToCourseConverter implements Converter<CourseCommand, Course> {
    @Synchronized
    @Nullable
    @Override
    public Course convert(CourseCommand source) {
        if (source == null) {
            return null;
        }
        final Course course = new Course();
        course.setName(source.getName());
        return course;
    }
}
