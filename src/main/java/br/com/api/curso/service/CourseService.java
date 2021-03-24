package br.com.api.curso.service;

import br.com.api.curso.controller.filter.CourseFilter;
import br.com.api.curso.exception.CourseException;
import br.com.api.curso.exception.CourseNotFoundException;
import br.com.api.curso.model.Course;
import br.com.api.curso.repository.CourseRepository;
import br.com.api.curso.validations.Validate;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.api.curso.repository.specifications.CourseSpecifications.*;

@Service
@AllArgsConstructor
public class CourseService {

    private final StudentCourseService studentCourseService;
    private final CourseRepository courseRepository;
    private final List<Validate<Course>> validations;

    public Course save(Course curso){
        validations.forEach(courseValidate -> courseValidate.valid(curso));
        return courseRepository.save(curso);
    }

    public Course findById(String id){
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    public void delete(String id){
        Course course = findById(id);
        if(!course.getStudents().isEmpty()){
            throw new CourseException("There are students enrolled in the course");
        }
        studentCourseService.removeAllStudentsFromCourse(id);
        courseRepository.deleteById(id);
    }

    public Page<Course> findAll(CourseFilter filter, Pageable pageable) {
        return courseRepository.findAll(createSpecificationsByFiltersWithAnd(filter), pageable);
    }

    public Course update(Course course) {
        if(!courseRepository.existsById(course.getId())){
            throw new CourseNotFoundException();
        }
        return save(course);
    }
}
