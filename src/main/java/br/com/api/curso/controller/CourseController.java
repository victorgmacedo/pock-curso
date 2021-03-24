package br.com.api.curso.controller;

import br.com.api.curso.controller.filter.CourseFilter;
import br.com.api.curso.dto.InfoCourseDTO;
import br.com.api.curso.dto.CourseDTO;
import br.com.api.curso.mapper.CourseMapper;
import br.com.api.curso.model.Course;
import br.com.api.curso.service.CourseService;
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

@RestController
@RequestMapping("courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<InfoCourseDTO> save(@Valid @RequestBody CourseDTO courseDTO){
        Course course = courseService.save(courseMapper.toCourse(courseDTO));
        return ResponseEntity.ok(courseMapper.toDTO(course));
    }

    @GetMapping("{id}")
    public ResponseEntity<InfoCourseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(courseMapper.toDTO(courseService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<InfoCourseDTO>> findAll(Pageable pageable, CourseFilter filters){
        return ResponseEntity.ok(courseService.findAll(filters, pageable).map(courseMapper::toDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<InfoCourseDTO> update(@PathVariable String id, @RequestBody CourseDTO courseDTO){
        Course course = courseMapper.toCourse(courseDTO);
        course.setId(id);
        return ResponseEntity.ok(courseMapper.toDTO(courseService.update(course)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
