package br.com.api.curso.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class StudentCourse extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;
    private LocalDateTime registrationDate;
    private LocalDateTime lowDate;

    public StudentCourse(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    @PrePersist
    private void prePersist(){
        this.registrationDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentCourse that = (StudentCourse) o;
        if(Objects.isNull(course) || !StringUtils.hasLength(course.getId()) || Objects.isNull(student) || !StringUtils.hasLength(student.getId())) return false;
        return course.getId().equals(that.course.getId()) && student.getId().equals(that.student.getId());
    }

}
