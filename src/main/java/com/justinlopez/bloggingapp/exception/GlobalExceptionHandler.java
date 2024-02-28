package com.justinlopez.bloggingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            {UserNotExistException.class,
            CategoryNotExistException.class,
            PostNotExistException.class,
            CommentNotExistException.class}
    )
    public ProblemDetail resourceNotFoundException(RuntimeException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({MissingRequiredFieldsException.class})
    public ProblemDetail badRequestException(RuntimeException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
