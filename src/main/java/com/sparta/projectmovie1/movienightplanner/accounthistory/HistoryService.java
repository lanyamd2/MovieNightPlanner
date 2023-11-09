package com.sparta.projectmovie1.movienightplanner.accounthistory;

import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.services.MovieService;
import com.sparta.projectmovie1.movienightplanner.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HistoryService {
    HistoryRepository historyRepository;
    MovieService movieService;
    SeriesService seriesService;
    @Autowired
    public HistoryService(HistoryRepository historyRepository,MovieService movieService,SeriesService seriesService) {
        this.historyRepository = historyRepository;
        this.movieService = movieService;
        this.seriesService = seriesService;
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

    public List<HistoryEntry> getAllHistoryEntriesByUserId(String userId){
        return historyRepository.findHistoryEntriesByUserId(userId);
    }

    public boolean isUserHistoryEmpty(String userId){
        return getAllHistoryEntriesByUserId(userId).isEmpty();
    }


    public Map<Date, Map<HistoryEntry, Production>> getAllUserHistoryByDate(String userId) {
        List<HistoryEntry> userHistory = getAllHistoryEntriesByUserId(userId);

        Map<Date, Map<HistoryEntry,Production>> userHistoryByDate = new TreeMap<>(Collections.reverseOrder());
        for(HistoryEntry entry : userHistory){
            Date date = entry.getDate();
            Production production = getProduction(entry);
            userHistoryByDate.computeIfAbsent(date, k-> new Hashtable<HistoryEntry,Production>());
            userHistoryByDate.get(date).putIfAbsent(entry,production);
        }
        return userHistoryByDate;
    }

    private Production getProduction(HistoryEntry entry) {
        if(entry.getMediaType().equals("movie")){
            return movieService.getMovieById(String.valueOf(entry.getProductionId()));
        }

        Production production = seriesService.getSeriesById(String.valueOf(entry.getProductionId()));
        production.setMedia_type("tv");
        return production;
    }
}
