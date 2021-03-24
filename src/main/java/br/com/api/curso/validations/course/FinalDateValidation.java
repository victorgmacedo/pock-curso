package br.com.api.curso.validations.course;

import br.com.api.curso.exception.CourseException;
import br.com.api.curso.model.Course;
import br.com.api.curso.validations.Validate;
import org.springframework.stereotype.Component;

import static java.util.Objects.*;

@Component
public class FinalDateValidation implements Validate<Course> {

    @Override
    public void valid(Course course) {
        validNull(course);
        if(isNull(course.getFinalDate())){
            throw new IllegalArgumentException("Final Date is null");
        }
        if(course.getFinalDate().isBefore(course.getInitialDate())){
            throw new CourseException("End date cannot be earlier than start date");
        }
    }

}
