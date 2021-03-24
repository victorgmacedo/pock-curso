package br.com.api.curso.mapper;

import br.com.api.curso.dto.CourseDTO;
import br.com.api.curso.dto.InfoCourseDTO;
import br.com.api.curso.dto.StudentsCourseDTO;
import br.com.api.curso.model.Course;
import br.com.api.curso.model.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.*;

@Mapper
public interface CourseMapper {

    @Mapping(source = "students", target = "students", qualifiedByName = "students")
    InfoCourseDTO toDTO(Course course);
    Course toCourse(CourseDTO dto);

    @Named("students")
    default Set<StudentsCourseDTO> studentCourseToDTO(Set<StudentCourse> students){
        if(isNull(students)){
            return Set.of();
        }
        return students.stream().map(StudentsCourseDTO::new).collect(Collectors.toSet());
    }
}
