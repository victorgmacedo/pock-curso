package br.com.api.curso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Student not enrolled in the course")
public class RegistrationNotFoundException extends RuntimeException {
}
