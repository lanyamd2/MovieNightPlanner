package com.sparta.projectmovie1.movienightplanner.accounthistory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HistoryEntryAlreadyExistsControllerAdvice {
    @ResponseBody
    @ExceptionHandler(HistoryEntryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    String historyEntryAlreadyExistsHandler(HistoryEntryAlreadyExistsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noSuchUserHandler(NoSuchUserException e){
        return e.getMessage();
    }



}
