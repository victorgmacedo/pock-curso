package br.com.api.curso.repository;

import br.com.api.curso.model.StudentCourse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCourseRepository extends PagingAndSortingRepository<StudentCourse, String> {
    Optional<StudentCourse> findByStudentIdAndCourseId(String studentId, String courseId);
    void deleteByCourseId(String courseId);
    void deleteByStudentId(String studantId);
}
