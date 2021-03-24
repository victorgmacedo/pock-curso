package br.com.api.curso.mapper;

import br.com.api.curso.dto.CoursesStudentDTO;
import br.com.api.curso.dto.InfoStudentDTO;
import br.com.api.curso.dto.StudentDTO;
import br.com.api.curso.model.Student;
import br.com.api.curso.model.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.*;

@Mapper
public interface StudentMapper {

    @Mapping(source = "courses", target = "courses", qualifiedByName = "courses")
    InfoStudentDTO toDTO(Student student);
    Student toStudent(StudentDTO dto);

    @Named("courses")
    default Set<CoursesStudentDTO> studentCourseToDTO(Set<StudentCourse> students){
        if(isNull(students)){
            return Set.of();
        }
        return students.stream().map(CoursesStudentDTO::new).collect(Collectors.toSet());
    }
}
