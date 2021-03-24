package br.com.api.curso.dto;

import br.com.api.curso.model.StudentCourse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class StudentsCourseDTO {

    private String name;
    private String nickname;
    private String documentNumber;
    private LocalDate birthDate;
    private LocalDateTime registrationDate;
    private LocalDateTime lowDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsCourseDTO that = (StudentsCourseDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(nickname, that.nickname) && Objects.equals(documentNumber, that.documentNumber) && Objects.equals(birthDate, that.birthDate) && Objects.equals(registrationDate, that.registrationDate) && Objects.equals(lowDate, that.lowDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nickname, documentNumber, birthDate, registrationDate, lowDate);
    }

    public StudentsCourseDTO (StudentCourse studentsCourse){
        setName(studentsCourse.getStudent().getName());
        setNickname(studentsCourse.getStudent().getNickname());
        setDocumentNumber(studentsCourse.getStudent().getDocumentNumber());
        setBirthDate(studentsCourse.getStudent().getBirthDate());
        setRegistrationDate(studentsCourse.getRegistrationDate());
        setLowDate(studentsCourse.getLowDate());
    }
}
