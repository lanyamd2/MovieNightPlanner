package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.controllers.ProfileController;
import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        movie.setMedia_type("movie");
        model.addAttribute("production",movie);
//        model.addAttribute("isMovie",true);
//        model.addAttribute("productionId", movie.getId());

        MyPlanEntry myPlanEntry = new MyPlanEntry();
        myPlanEntry.setId(id);
        myPlanEntry.setMovie(true);
        model.addAttribute("myPlanEntry", myPlanEntry);
        return "movie-profile-page";
    }

    @GetMapping("tv/{id}")
    private String getShowById(Model model, @PathVariable("id") String id){
        Series series = profileController.getSeriesById(id);
        series.setMedia_type("show");
        model.addAttribute("production",series);
//        model.addAttribute("isMovie",false);
//        model.addAttribute("productionId",series.getId());

        MyPlanEntry myPlanEntry = new MyPlanEntry();
        myPlanEntry.setId(id);
        myPlanEntry.setMovie(false);
        model.addAttribute("myPlanEntry", myPlanEntry);
        return "tv-profile-page";
    }

//    @PostMapping("/myplan/create")
//    private String addProductionToMyPlan(@ModelAttribute("myPlanEntry") MyPlanEntry myPlanEntry){
//
//        return "my-plan";
//    }



}
