package com.sparta.projectmovie1.movienightplanner.accounthistory;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HistoryController {
    HistoryRepository historyRepository;
    HistoryService historyService;

    @Autowired
    public HistoryController(HistoryRepository historyRepository, HistoryService historyService) {
        this.historyRepository = historyRepository;
        this.historyService = historyService;
    }

    //Create
    @PostMapping("/history")
    public HistoryEntry create(@Valid @RequestBody HistoryEntry historyEntry){
        //check if movie is already in history
        return historyService.createEntry(historyEntry);
    }

    //Read

    //Update
    //Delete

}
