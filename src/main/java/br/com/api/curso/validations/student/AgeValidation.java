package br.com.api.curso.validations.student;

import br.com.api.curso.exception.StudentException;
import br.com.api.curso.model.Student;
import br.com.api.curso.validations.Validate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class AgeValidation implements Validate<Student> {

    private static final long MIN_AGE = 18;

    @Override
    public void valid(Student student) {
        validNull(student);
        LocalDate now = LocalDate.now();
        long diff = ChronoUnit.YEARS.between(student.getBirthDate(),now);
        if(diff < MIN_AGE){
            throw new StudentException(String.format("Minimum age is %s", MIN_AGE));
        }
    }
}
