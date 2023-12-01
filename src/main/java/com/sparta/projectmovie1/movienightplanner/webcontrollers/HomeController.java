package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import com.sparta.projectmovie1.movienightplanner.models.*;
import com.sparta.projectmovie1.movienightplanner.services.ProviderService;
import com.sparta.projectmovie1.movienightplanner.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private SearchService searchService;
    private ProviderService providerService;

    @Autowired
    public HomeController(SearchService searchService,ProviderService providerService) {
        this.searchService = searchService;
        this.providerService=providerService;
    }


    @RequestMapping(value = {"/index","/home","/"})
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
                                     @RequestParam(required = false) String sortBy, Model model,
                                     @AuthenticationPrincipal SecurityUser user){



        if(page==null || page==0){
            page=1;
        }


        LastSearchCriteria lastSearchCriteria=new LastSearchCriteria(searchQuery,productionType,searchGenre);
        model.addAttribute("lastSearchCriteria",lastSearchCriteria);

        List<Genre> genres=searchService.getGenreList(productionType).getGenres();
        model.addAttribute("lastSearchGenreName",searchGenre!=0?searchService.getGenreName(genres,searchGenre):null);


        ProductionList productionList=searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page,user);
        List<Production> productions=productionList.getResults();

        if(sortBy!=null && sortBy.equals("popularity")){
            productions=searchService.sortResultByPopularityNew(productions);
        }

        model.addAttribute("productions",productions);
        model.addAttribute("page",productionList.getPage());
        model.addAttribute("totalpages",productionList.getTotal_pages());

        MyPlanEntry myPlanEntry=new MyPlanEntry();
        model.addAttribute("myPlanEntry",myPlanEntry);

        if(user!=null &&
                (searchQuery==null || searchQuery.equals("")) &&
                providerService.getCurrentProviders(user.getUser().getId()).size()>0 &&
                searchService.getStreamingSearchString(productionType,user).equals("")){
            model.addAttribute("savedProviderNotSupportedForProductionTypeError",
                    "None of the saved providers supports the production type("+productionType+") you searched for." +
                                "Have a look at the following from other providers");
        }

        return "results";
    }

}
