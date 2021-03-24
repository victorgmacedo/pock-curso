package br.com.api.curso.service;

import br.com.api.curso.exception.CourseException;
import br.com.api.curso.exception.RegistrationNotFoundException;
import br.com.api.curso.exception.StudentException;
import br.com.api.curso.model.Course;
import br.com.api.curso.model.Student;
import br.com.api.curso.model.StudentCourse;
import br.com.api.curso.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {

    @Lazy
    @Autowired
    private  CourseService courseService;
    @Autowired
    private  StudentService studentService;
    @Autowired
    private  StudentCourseRepository studentCourseRepository;

    @Transactional
    public Student create(String studentId, Set<String> coursesId){
        Student student = studentService.findById(studentId);
        Set<Course> courses = coursesId.stream().map(courseService::findById).collect(Collectors.toSet());
        validate(student, courses);
        return student;
    }

    private void validate(Student student, Set<Course> courses) {
        courses.forEach(course -> {
            if(course.hasStudent(student)){
                throw new StudentException(String.format("Student already enrolled in the course %s", course.getName()));
            }
            if(course.isClosed()){
                throw new CourseException(String.format("Course %s has no vacancies", course.getName()));
            }
            studentCourseRepository.save(new StudentCourse(course, student));
        });
    }

    public void removeStudentFromCourse(String studentId, String courseId){
        StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId).orElseThrow(RegistrationNotFoundException::new);
        studentCourse.setLowDate(LocalDateTime.now());
        studentCourseRepository.save(studentCourse);
    }

    @Transactional
    public void removeAllStudentsFromCourse(String courseId){
        studentCourseRepository.deleteByCourseId(courseId);
    }
    public void removeAllCoursesFromStudant(String studantId){
        studentCourseRepository.deleteByStudentId(studantId);
    }
}
