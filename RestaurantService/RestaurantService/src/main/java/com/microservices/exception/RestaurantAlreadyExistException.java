package com.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestaurantAlreadyExistException extends RuntimeException{

    public RestaurantAlreadyExistException(String message) {
        super(message);
    }
}
