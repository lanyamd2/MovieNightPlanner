package com.sparta.projectmovie1.movienightplanner.controller;

import com.sparta.projectmovie1.movienightplanner.model.LastSearchCriteria;
import com.sparta.projectmovie1.movienightplanner.model.Production;
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


    @RequestMapping("/index")
    public String showIndexPage(@RequestParam(required = false) String timeWindow,
                               @RequestParam(required = false) String sortBy,
                               Model model){

        System.out.println(timeWindow+"--------------"+sortBy);
        if(timeWindow==null){
            timeWindow="day";
        }
        List<Production> trendingProductions=searchService.getTrendingproductionsNew(timeWindow);
        if(sortBy!=null && sortBy.equals("popularity")){
            System.out.println("sort by popularity called..");
            trendingProductions=searchService.sortResultByPopularityNew(trendingProductions);
        }
        model.addAttribute("productions",trendingProductions);
        model.addAttribute("selectedTimeWindow",timeWindow.equals("day")?"Today":"This Week");
        return "index.html";
    }
    
    @RequestMapping("/search-results-new")
    public String showResultsPageNew(@RequestParam(required = false) String searchQuery,
                                     @RequestParam(required = false) String productionType,
                                     @RequestParam(required = false) Integer searchGenre,
                                     @RequestParam(required = false) String sortBy, Model model){

        System.out.println(searchQuery+"------------"+productionType+"---------------"+searchGenre);

        if(searchQuery.equals("")){
            System.out.println("searchQuery is null");
        }

        LastSearchCriteria lastSearchCriteria=new LastSearchCriteria(searchQuery,productionType,searchGenre);
        model.addAttribute("lastSearchCriteria",lastSearchCriteria);

        List<Production> productions=searchService.getAllSearchResultsNew(searchQuery,productionType,searchGenre);

        if(sortBy!=null && sortBy.equals("popularity")){
            System.out.println("sort by popularity called..");
            productions=searchService.sortResultByPopularityNew(productions);
        }
        model.addAttribute("productions",productions);
        return "results";
    }
}
