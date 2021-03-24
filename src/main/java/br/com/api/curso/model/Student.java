package br.com.api.curso.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Student extends AbstractEntity {

    private String name;
    private String nickname;
    private String documentNumber;
    private LocalDate birthDate;
    @Column(updatable = false)
    private LocalDateTime registrationDate;
    @OneToMany(mappedBy = "student" ,fetch = FetchType.LAZY)
    @Where(clause = "low_date is null")
    private Set<StudentCourse> courses;

    @PrePersist
    private void prePersist(){
        this.registrationDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(documentNumber, student.documentNumber) || student.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), documentNumber);
    }
}
