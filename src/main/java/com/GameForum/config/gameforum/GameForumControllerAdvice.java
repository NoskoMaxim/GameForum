package com.gameforum.config.gameforum;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GameForumControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {GameForumException.class})
    protected ResponseEntity handleDemoException(
            GameForumException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getFailures(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}