package com.example.dabadoccodingchallenge.config;

import com.example.dabadoccodingchallenge.entitys.ErrorEntity;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorEntity> handleexception(AppException appException){
        return ResponseEntity.status(appException.getHttpStatus())
                .body(new ErrorEntity(appException.getMessage()));
    }

}
