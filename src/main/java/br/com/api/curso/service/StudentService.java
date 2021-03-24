package br.com.api.curso.service;

import br.com.api.curso.controller.filter.StudentFilter;
import br.com.api.curso.exception.StudentNotFoundException;
import br.com.api.curso.model.Student;
import br.com.api.curso.repository.StudentRepository;
import br.com.api.curso.validations.Validate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.api.curso.repository.specifications.StudentSpecifications.*;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentCourseService studentCourseService;
    private final StudentRepository studentRepository;
    private final List<Validate<Student>> validations;

    public Student save(Student student){
        validations.forEach(studentValidate -> studentValidate.valid(student));
        return studentRepository.save(student);
    }

    public Student findById(String id){
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }
    public void delete(String id){
        studentCourseService.removeAllCoursesFromStudant(id);
        studentRepository.deleteById(id);
    }
    public Page<Student> findAll(StudentFilter filter, Pageable pageable) {
        return studentRepository.findAll(createSpecificationsByFiltersWithAnd(filter), pageable);
    }

    public Student update(Student student) {
        if(!studentRepository.existsById(student.getId())){
            throw new StudentNotFoundException();
        }
        return save(student);
    }
}
