package dev.jaczerob.resistance.api.controllers;

import dev.jaczerob.resistance.api.exceptions.ResistanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResistanceException.class)
    public ProblemDetail handleResistanceException(final ResistanceException exc) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exc.getMessage());
        problemDetail.setTitle(exc.getClass().getSimpleName());
        return problemDetail;
    }
}
