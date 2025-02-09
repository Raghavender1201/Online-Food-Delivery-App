package com.microservices.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.CONFLICT)
public class MenuItemAlreadyExistsException extends RuntimeException{

    public MenuItemAlreadyExistsException(String message) {
        super(message);
    }
}
