package br.com.api.curso.repository;

import br.com.api.curso.model.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, String>, JpaSpecificationExecutor<Student> {
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumberAndIdNot(String documentNumber, String id);
}
