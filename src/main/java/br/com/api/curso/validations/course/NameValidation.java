package br.com.api.curso.validations.course;

import br.com.api.curso.exception.CourseException;
import br.com.api.curso.model.Course;
import br.com.api.curso.repository.CourseRepository;
import br.com.api.curso.validations.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@AllArgsConstructor
public class NameValidation implements Validate<Course> {

    private final CourseRepository courseRepository;

    @Override
    public void valid(Course course) {
        validNull(course);
        boolean validation = (!StringUtils.hasLength(course.getId()) && courseRepository.existsByName(course.getName().trim()))
                || (StringUtils.hasLength(course.getId()) && courseRepository.existsByNameAndIdNot(course.getName().trim(), course.getId()));

        if (validation){
            throw new CourseException(String.format("Course with name %s already exists", course.getName()));
        }
    }

}
