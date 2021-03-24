package br.com.api.curso.repository;

import br.com.api.curso.model.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, String>, JpaSpecificationExecutor<Course> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);

}
