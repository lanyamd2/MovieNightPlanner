package com.sparta.projectmovie1.movienightplanner.services.exceptions;

public class ProductionNotFoundException extends RuntimeException{
    public ProductionNotFoundException(String message) {
        super(message);
    }
}
