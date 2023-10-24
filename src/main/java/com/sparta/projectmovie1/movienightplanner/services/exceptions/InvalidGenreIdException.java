package com.sparta.projectmovie1.movienightplanner.services.exceptions;

public class InvalidGenreIdException extends RuntimeException{

    public InvalidGenreIdException(String message){
        super(message);
    }
}
