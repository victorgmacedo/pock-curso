package br.com.api.curso.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Course extends AbstractEntity {

    private String name;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private Integer maxNumberStudents;
    @Column(updatable = false)
    private LocalDateTime registrationDate;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @Where(clause = "low_date is null")
    private Set<StudentCourse> students;

    @PrePersist
    private void prePersist(){
        this.registrationDate = LocalDateTime.now();
    }

    public boolean isClosed(){
        return maxNumberStudents.equals(students.size());
    }
    public boolean hasStudent(Student student){
        return students.stream().filter(studentCourse -> studentCourse.getStudent().equals(student)).count() > 0;
    }
}