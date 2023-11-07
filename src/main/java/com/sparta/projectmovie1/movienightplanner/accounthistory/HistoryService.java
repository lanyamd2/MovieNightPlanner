package com.sparta.projectmovie1.movienightplanner.accounthistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    HistoryRepository historyRepository;
    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public HistoryEntry createEntry(HistoryEntry historyEntry){
        return historyRepository.save(historyEntry);
    }

}
