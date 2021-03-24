package br.com.api.curso.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class StudentDTO {

    @Size(min = 3, max = 100)
    @NotEmpty
    private String name;
    @Size(min = 2, max = 100)
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String documentNumber;
    @Past
    private LocalDate birthDate;

}
