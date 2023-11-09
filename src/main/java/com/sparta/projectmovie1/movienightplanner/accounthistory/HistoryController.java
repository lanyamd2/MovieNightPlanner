package com.sparta.projectmovie1.movienightplanner.accounthistory;

import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.users.User;
import com.sparta.projectmovie1.movienightplanner.services.MovieService;
import com.sparta.projectmovie1.movienightplanner.services.SeriesService;
import com.sparta.projectmovie1.movienightplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/securedapi")
public class HistoryController {

    HistoryRepository historyRepository;
    HistoryService historyService;
    UserService userService;
    MovieService movieService;
    SeriesService seriesService;

    @Autowired
    public HistoryController(HistoryRepository historyRepository, HistoryService historyService, UserService userService, MovieService movieService, SeriesService seriesService) {
        this.historyRepository = historyRepository;
        this.historyService = historyService;
        this.userService = userService;
        this.movieService = movieService;
        this.seriesService = seriesService;
    }

    @GetMapping("/history/all/{username}")
    public List<HistoryEntry> getAllUserHistory(@PathVariable("username") String username){
        Optional<User> user = userService.findByUsername(username);
        if(user.isEmpty()){
            throw new NoSuchUserException("There is no user with the username : "+username);
        }
        return historyService.getAllHistoryEntriesByUserId(user.get().getId());
    }

    @GetMapping("/history/all/bydate/{username}")
    public Map<Date,Map<HistoryEntry, Production>> getAllUserHistoryByUsernameOrderedByDate(@PathVariable("username") String username){
        Optional<User> user = userService.findByUsername(username);
        if(user.isEmpty()){
            throw new NoSuchUserException("There is no user with the username : "+username);
        }
        return historyService.getAllUserHistoryByDate(user.get().getId());
    }

    @GetMapping("/history/delete/{historyEntryId}")
    public void deleteHistoryEntryById(@PathVariable("historyEntryId") String entryId){
        Optional<HistoryEntry> historyEntryOptional = historyRepository.findHistoryEntryById(entryId);
        if(historyEntryOptional.isEmpty()){
            throw new NoSuchHistoryEntryException("There is no history entry with the id : "+entryId);
        }

        historyRepository.deleteById(entryId);
    }

}
