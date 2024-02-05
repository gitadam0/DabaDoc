package com.example.dabadoccodingchallenge.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Data
public class AppException extends Exception {
     HttpStatus httpStatus;
    public AppException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus=httpStatus;
    }
}
