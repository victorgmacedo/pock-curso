package br.com.api.curso.validations.student;

import br.com.api.curso.exception.CourseException;
import br.com.api.curso.model.Student;
import br.com.api.curso.repository.StudentRepository;
import br.com.api.curso.validations.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@AllArgsConstructor
public class DocumentNumberValidation implements Validate<Student> {

    private final StudentRepository studentRepository;

    @Override
    public void valid(Student student) {
        validNull(student);
        boolean validation = (!StringUtils.hasLength(student.getId()) && studentRepository.existsByDocumentNumber(student.getDocumentNumber().trim()))
                || (StringUtils.hasLength(student.getId()) && studentRepository.existsByDocumentNumberAndIdNot(student.getDocumentNumber().trim(), student.getId()));

        if (validation){
            throw new CourseException(String.format("Student with document %s already exists", student.getDocumentNumber()));
        }
    }

}
