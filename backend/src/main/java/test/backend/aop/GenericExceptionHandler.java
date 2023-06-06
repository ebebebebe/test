package test.backend.aop;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import test.backend.exception.EmailAlreadyInUsedException;
import test.backend.model.dto.ApiErrorResponse;

import java.util.Set;
import java.util.StringJoiner;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyInUsedException.class)
    public ApiErrorResponse handleEmailAlreadyExists(EmailAlreadyInUsedException e) {
        return ApiErrorResponse.from(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiErrorResponse handleValidationError(ConstraintViolationException e) {
        StringJoiner messageJoiner = new StringJoiner("\n");
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation violation : constraintViolations) {
            messageJoiner.add(violation.getMessage());
        }
        return ApiErrorResponse.from(messageJoiner.toString());
    }
}
