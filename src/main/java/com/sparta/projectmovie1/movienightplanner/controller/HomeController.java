package com.sparta.projectmovie1.movienightplanner.controller;

import com.sparta.projectmovie1.movienightplanner.model.TrendingProduction;
import com.sparta.projectmovie1.movienightplanner.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private SearchService searchService;

    @Autowired
    public HomeController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/home")
    public String showHomePage(@RequestParam(required = false) String timeWindow,
                               @RequestParam(required = false) String sortBy,
                               Model model){

        System.out.println(timeWindow+"--------------"+sortBy);
        if(timeWindow==null){
            timeWindow="day";
        }
        List<TrendingProduction> trendingProductions=searchService.getTrendingproductions(timeWindow);
        if(sortBy!=null && sortBy.equals("popularity")){
            System.out.println("sort by popularity called..");
            trendingProductions=searchService.sortResultByPopularity(trendingProductions);
        }
        model.addAttribute("productions",trendingProductions);
        model.addAttribute("selectedTimeWindow",timeWindow.equals("day")?"Today":"This Week");
        return "index.html";
    }

    @RequestMapping("/search-results")
    public String showResultsPage(@RequestParam(required = false) String searchQuery,
                                  @RequestParam(required = false) String productionType,
                                  @RequestParam(required = false) Integer searchGenre,Model model){
        System.out.println(searchQuery+"------------"+productionType+"---------------"+searchGenre);
        if(searchQuery.equals("")){
            System.out.println("searchQuery is null");
        }

        model.addAttribute("productions",searchService.getAllSearchResults(searchQuery,productionType,searchGenre));
        return "results";
    }
}
