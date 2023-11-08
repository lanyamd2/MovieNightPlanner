package com.sparta.projectmovie1.movienightplanner.accounthistory;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public HistoryEntry createEntry(@Valid @RequestBody HistoryEntry entry){
        //check if movie is already in history
        if(historyService.isExistingHistoryEntry(entry)) {
            throw new HistoryEntryAlreadyExistsException("Already added to your watch history");
        }
        return historyService.createEntry(entry);
    }

    //Read - use hateoas link to each production

    //Update

    //Delete

}
