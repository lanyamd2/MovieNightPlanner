package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.models.LastSearchCriteria;
import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.ProductionList;
import com.sparta.projectmovie1.movienightplanner.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private SearchService searchService;

    @Autowired
    public HomeController(SearchService searchService) {
        this.searchService = searchService;
    }


    @RequestMapping(value = {"/index","/home","/"},method = RequestMethod.GET)
    public String showIndexPage(@RequestParam(required = false) String timeWindow,
                               @RequestParam(required = false) String sortBy,
                               Model model){

        if(timeWindow==null){
            timeWindow="day";
        }

        List<Production> trendingProductions=searchService.getTrendingproductionsNew(timeWindow);

        if(sortBy!=null && sortBy.equals("popularity")){
            trendingProductions=searchService.sortResultByPopularityNew(trendingProductions);
        }

        model.addAttribute("productions",trendingProductions);
        model.addAttribute("selectedTimeWindow",timeWindow.equals("day")?"Today":"This Week");

        MyPlanEntry myPlanEntry=new MyPlanEntry();
        model.addAttribute("myPlanEntry",myPlanEntry);

        return "index";
    }
    
    @RequestMapping("/search-results-new")
    public String showResultsPageNew(@RequestParam(required = false) String searchQuery,
                                     @RequestParam String productionType,
                                     @RequestParam Integer searchGenre,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) String sortBy, Model model){



        if(page==null || page==0){
            page=1;
        }
        LastSearchCriteria lastSearchCriteria=new LastSearchCriteria(searchQuery,productionType,searchGenre);
        model.addAttribute("lastSearchCriteria",lastSearchCriteria);

        //List<Production> productions=searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page);
        ProductionList productionList=searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page);
        List<Production> productions=productionList.getResults();

        if(sortBy!=null && sortBy.equals("popularity")){
            productions=searchService.sortResultByPopularityNew(productions);
        }

        model.addAttribute("productions",productions);
        model.addAttribute("page",productionList.getPage());
        model.addAttribute("totalpages",productionList.getTotal_pages());

        MyPlanEntry myPlanEntry=new MyPlanEntry();
        model.addAttribute("myPlanEntry",myPlanEntry);

        return "results";
    }

}
