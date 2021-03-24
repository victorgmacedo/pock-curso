package br.com.api.curso.controller.filter;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface StudentFilter {

    String getName();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate getBirthDate();

}
