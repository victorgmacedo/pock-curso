package br.com.api.curso.repository.specifications;

import br.com.api.curso.controller.filter.CourseFilter;
import br.com.api.curso.model.Course;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static java.util.Objects.*;

public class CourseSpecifications {

    private CourseSpecifications(){

    }

    public static Specification<Course> nameLike(String name) {
       return (course, cq, cb) -> cb.like(course.get("name"),"%".concat(name).concat("%"));
    }

    public static Specification<Course> initialDateEquals(LocalDate initialDate) {
        return (course, cq, cb) ->  cb.equal(cb.function("date", LocalDate.class, course.get("initialDate")),initialDate);
    }

    public static Specification<Course> finalDateEquals(LocalDate initialDate) {
        return (course, cq, cb) ->  cb.equal(cb.function("date", LocalDate.class, course.get("initialDate")),initialDate);
    }

    public static Specification<Course> createSpecificationsByFiltersWithAnd(CourseFilter filter){
        Specification<Course> specifications = null;
        if (nonNull(filter.getName())){
            specifications = nameLike(filter.getName());
        }
        if(nonNull(filter.getInitialDate())){
            specifications = nonNull(specifications)
                    ? specifications.and(initialDateEquals(filter.getInitialDate()))
                    : initialDateEquals(filter.getInitialDate());
        }
        if(nonNull(filter.getFinalDate())){
            specifications = nonNull(specifications)
                    ? specifications.and(finalDateEquals(filter.getFinalDate()))
                    : finalDateEquals(filter.getFinalDate());
        }
        return specifications;
    }

}
