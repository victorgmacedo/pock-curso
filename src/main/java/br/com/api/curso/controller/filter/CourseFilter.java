package br.com.api.curso.controller.filter;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface CourseFilter{

    String getName();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate getInitialDate();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate getFinalDate();

}
