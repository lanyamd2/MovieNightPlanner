package com.sparta.projectmovie1.movienightplanner.accounthistory;

import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.users.User;
import com.sparta.projectmovie1.movienightplanner.services.MovieService;
import com.sparta.projectmovie1.movienightplanner.services.SeriesService;
import com.sparta.projectmovie1.movienightplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
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

}
