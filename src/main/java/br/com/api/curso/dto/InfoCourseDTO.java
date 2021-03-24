package br.com.api.curso.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class InfoCourseDTO {

    private String id;
    private String name;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private Integer maxNumberStudents;
    private LocalDateTime registrationDate;
    private Set<StudentsCourseDTO> students;
}
