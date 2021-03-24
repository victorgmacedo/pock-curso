package br.com.api.curso.dto;

import br.com.api.curso.model.StudentCourse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CoursesStudentDTO {

    private String name;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;

    public CoursesStudentDTO(StudentCourse studentsCourse){
        setName(studentsCourse.getCourse().getName());
        setInitialDate(studentsCourse.getCourse().getInitialDate());
        setFinalDate(studentsCourse.getCourse().getFinalDate());
    }
}
