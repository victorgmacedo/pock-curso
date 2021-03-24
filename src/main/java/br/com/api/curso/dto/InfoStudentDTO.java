package br.com.api.curso.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class InfoStudentDTO {
    private String id;
    private String name;
    private String nickname;
    private String documentNumber;
    private LocalDate birthDate;
    private LocalDateTime registrationDate;
    private Set<CoursesStudentDTO> courses;

}
