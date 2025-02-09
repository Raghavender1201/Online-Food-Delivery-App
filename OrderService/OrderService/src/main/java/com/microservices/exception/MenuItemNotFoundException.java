package com.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MenuItemNotFoundException extends RuntimeException{

    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
