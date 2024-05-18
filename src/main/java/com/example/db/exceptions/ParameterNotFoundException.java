package com.example.db.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Parameter not found.")
public class ParameterNotFoundException extends RuntimeException {
    public ParameterNotFoundException(String msg) {
        super(msg);
    }
    public ParameterNotFoundException() {
        super("Parameter not found.");
    }

}
