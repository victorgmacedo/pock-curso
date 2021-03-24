package br.com.api.curso.controller;

import br.com.api.curso.controller.filter.StudentFilter;
import br.com.api.curso.dto.InfoStudentDTO;
import br.com.api.curso.dto.StudentDTO;
import br.com.api.curso.mapper.StudentMapper;
import br.com.api.curso.model.Student;
import br.com.api.curso.service.StudentCourseService;
import br.com.api.curso.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentCourseService studentCourseService;
    private final StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<InfoStudentDTO> save(@Valid @RequestBody StudentDTO studentDTO){
        Student student = studentService.save(studentMapper.toStudent(studentDTO));
        return ResponseEntity.ok(studentMapper.toDTO(student));
    }

    @PostMapping("{studentId}")
    public ResponseEntity<InfoStudentDTO> save(@PathVariable String studentId, @RequestBody Set<String> courses){
        Student student = studentCourseService.create(studentId, courses);
        return ResponseEntity.ok(studentMapper.toDTO(student));
    }

    @DeleteMapping("{studentId}/course/{courseId}")
    public ResponseEntity<InfoStudentDTO> save(@PathVariable String studentId, @PathVariable String courseId){
        studentCourseService.removeStudentFromCourse(studentId, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<InfoStudentDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(studentMapper.toDTO(studentService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<InfoStudentDTO>> findAll(Pageable pageable, StudentFilter filters){
        return ResponseEntity.ok(studentService.findAll(filters, pageable).map(studentMapper::toDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<InfoStudentDTO> update(@PathVariable String id, @RequestBody StudentDTO studentDTO){
        Student student = studentMapper.toStudent(studentDTO);
        student.setId(id);
        return ResponseEntity.ok(studentMapper.toDTO(studentService.update(student)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
