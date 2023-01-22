package com.aboshady.clinic.advice;

import com.aboshady.clinic.models.ErrorDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto processConstraintError(DataIntegrityViolationException ex) {
        String sqlMessage = (ex.getRootCause() != null) ? ex.getRootCause().getMessage() : ex.getMessage();
        String message = sqlMessage==null? "error while persisting data, your data is not valid": sqlMessage;
        return new ErrorDto(message);

    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDto processNotFoundException(NotFoundException ex) {
        String message = ex.getMessage()==null? "Requested resource is not found": ex.getMessage();
        return new ErrorDto(message);

    }
}
