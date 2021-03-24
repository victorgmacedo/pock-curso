package br.com.api.curso.repository.specifications;

import br.com.api.curso.controller.filter.StudentFilter;
import br.com.api.curso.model.Student;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static java.util.Objects.*;

public class StudentSpecifications {

    private StudentSpecifications(){
    }

    public static Specification<Student> nameLike(String name) {
       return (student, cq, cb) -> cb.like(student.get("name"),"%".concat(name).concat("%"));
    }

    public static Specification<Student> birthDateEquals(LocalDate birthDate) {
        return (student, cq, cb) ->  cb.equal(cb.function("date", LocalDate.class, student.get("birthDate")),birthDate);
    }

    public static Specification<Student> createSpecificationsByFiltersWithAnd(StudentFilter filter){
        Specification<Student> specifications = null;
        if (nonNull(filter.getName())){
            specifications = nameLike(filter.getName());
        }
        if(nonNull(filter.getBirthDate())){
            specifications = nonNull(specifications)
                    ? specifications.and(birthDateEquals(filter.getBirthDate()))
                    : birthDateEquals(filter.getBirthDate());
        }
        return specifications;
    }

}
