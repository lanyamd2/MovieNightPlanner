package com.sparta.projectmovie1.movienightplanner.accounthistory;

public class NoSuchHistoryEntryException extends RuntimeException{
    public NoSuchHistoryEntryException(String message) {
        super(message);
    }
}
