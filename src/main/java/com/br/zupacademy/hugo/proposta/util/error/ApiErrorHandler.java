package com.br.zupacademy.hugo.proposta.util.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ApiErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentsNotValid(MethodArgumentNotValidException exception){
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors()
                .stream().forEach(fieldError -> {
                    errors.add(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
        });


        exception.getBindingResult().getGlobalErrors()
                .stream().forEach(globalError -> {
                    errors.add(messageSource.getMessage(globalError, LocaleContextHolder.getLocale()));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponse(errors));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleResponseStatusException(ResponseStatusException exception){
        return ResponseEntity.status(exception.getStatus()).body(new ApiErrorResponse(Collections.singletonList(exception.getReason())));
    }
}
