package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.controllers.ProfileController;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/details")
public class ProfileWebController {

    private ProfileController profileController;

    @Autowired
    public ProfileWebController(ProfileController profileController){
        this.profileController=profileController;
    }

    @GetMapping("movie/{id}")
    private String getMovieById(Model model, @PathVariable("id") String id){
        Movie movie = profileController.getMovieById(id);
        model.addAttribute("movie",movie);
        return "movie-profile-page";
    }

    @GetMapping("tv/{id}")
    private String getShowById(Model model, @PathVariable("id") String id){
        Series series = profileController.getSeriesById(id);
        model.addAttribute("show",series);
        return "tv-profile-page";
    }


}
