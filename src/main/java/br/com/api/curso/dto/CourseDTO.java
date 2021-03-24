package br.com.api.curso.dto;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CourseDTO {

    @Size(min = 3, max = 100)
    @NotEmpty
    private String name;
    @Future
    @NotNull
    private LocalDateTime initialDate;
    @Future
    @NotNull
    private LocalDateTime finalDate;
    @Min(1)
    @NotNull
    private Integer maxNumberStudents;

}
