package com.sparta.projectmovie1.movienightplanner.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductionNotFoundControllerAdvice {
    @ResponseBody
    @ExceptionHandler(ProductionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productionNotFoundHandler(ProductionNotFoundException e){
        return e.getMessage();
    }
}
