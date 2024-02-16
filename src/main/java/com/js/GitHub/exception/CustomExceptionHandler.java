package com.js.GitHub.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    protected ResponseEntity<CustomExceptionBody> notFoundException() {
        CustomExceptionBody customExceptionBody = new CustomExceptionBody(HttpStatus.NOT_FOUND.value(), "User not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(customExceptionBody);
    }

}