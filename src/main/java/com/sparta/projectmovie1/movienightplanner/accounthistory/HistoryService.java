package com.sparta.projectmovie1.movienightplanner.accounthistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<HistoryEntry> findEntry(HistoryEntry entry){
        return historyRepository.findHistoryEntryByUserIdAndProductionIdAndDate(entry.getUserId(), entry.getProductionId(),entry.getDate());
    }

    public boolean isExistingHistoryEntry(HistoryEntry entry){
        return findEntry(entry).isPresent();
    }



}
