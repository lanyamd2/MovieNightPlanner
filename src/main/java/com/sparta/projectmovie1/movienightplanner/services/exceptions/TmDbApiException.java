package com.sparta.projectmovie1.movienightplanner.services.exceptions;

public class TmDbApiException extends RuntimeException{

    public TmDbApiException(String message){
        super(message);
    }
}
