package com.sparta.projectmovie1.movienightplanner.accounthistory;

public class HistoryEntryAlreadyExistsException extends RuntimeException{
    public HistoryEntryAlreadyExistsException(String message) {
        super(message);
    }
}
