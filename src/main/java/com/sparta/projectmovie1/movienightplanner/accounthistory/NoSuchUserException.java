package com.sparta.projectmovie1.movienightplanner.accounthistory;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String message) {
        super(message);
    }
}
