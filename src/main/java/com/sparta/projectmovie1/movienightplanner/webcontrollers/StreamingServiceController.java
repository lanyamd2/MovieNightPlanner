package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import com.sparta.projectmovie1.movienightplanner.models.MyProviderEntry;
import com.sparta.projectmovie1.movienightplanner.models.Provider;
import com.sparta.projectmovie1.movienightplanner.repositories.MyProviderEntryRepository;
import com.sparta.projectmovie1.movienightplanner.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StreamingServiceController {

    ProviderService providerService;

    MyProviderEntryRepository myProviderEntryRepo;

    @Autowired
    public StreamingServiceController(ProviderService providerService,MyProviderEntryRepository myProviderEntryRepo) {
        this.providerService = providerService;
        this.myProviderEntryRepo=myProviderEntryRepo;
    }

    @RequestMapping("/providers")
    public String showStreamingServices(Model model,@AuthenticationPrincipal SecurityUser securityUser){
        String userId = securityUser.getUser().getId();
        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));
        return "streaming-services";
    }

    @RequestMapping("/providers/movie")
    public String showProvidersForMovie(Model model,@AuthenticationPrincipal SecurityUser securityUser){
        String userId = securityUser.getUser().getId();
        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));
        model.addAttribute("movieProviders",providerService.getAllProvidersFromTmdb("movie"));
        model.addAttribute("type","movie");
        return "streaming-services";
    }
    @RequestMapping("/providers/tv")
    public String showProvidersForTv(Model model,@AuthenticationPrincipal SecurityUser securityUser){
        String userId = securityUser.getUser().getId();
        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));
        model.addAttribute("tvProviders",providerService.getAllProvidersFromTmdb("tv"));
        model.addAttribute("type","tv");
        return "streaming-services";
    }

    @RequestMapping("/addToProviders")
    public String addToMyProviders(@RequestParam Integer providerId,Model model, @AuthenticationPrincipal SecurityUser securityUser){
        String userId = securityUser.getUser().getId();
        System.out.println("providerId---"+providerId);

        MyProviderEntry existingProviderEntry=myProviderEntryRepo.findByUserIdAndProviderId(userId,providerId);

        if(existingProviderEntry==null){

            MyProviderEntry myProviderEntry=new MyProviderEntry();
            myProviderEntry.setProviderId(providerId);
            myProviderEntry.setUserId(userId);

            providerService.addToMyProviderEntries(myProviderEntry);
        }
        else{
            model.addAttribute("addToProviderError","Provider already added");
        }

        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));


        return "streaming-services";

    }


    @RequestMapping("/removeProvider")
    public String removeFromMyProviders(@RequestParam Integer providerId,Model model, @AuthenticationPrincipal SecurityUser securityUser){
        String userId = securityUser.getUser().getId();
        providerService.deleteProvider(providerId,userId);
        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));
        return "streaming-services";
    }

    @RequestMapping("/searchProvider/{productionType}")
    public String showSearchedProvider(@PathVariable String productionType,@RequestParam String searchedProvider, Model model,@AuthenticationPrincipal SecurityUser securityUser ){

        String userId = securityUser.getUser().getId();
        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));
        model.addAttribute("type",productionType);
        if(productionType.equals("movie")){
            model.addAttribute("movieProviders",providerService.getSearchedProvider(productionType,searchedProvider));
        }
        else{
            model.addAttribute("tvProviders",providerService.getSearchedProvider(productionType,searchedProvider));
        }
        if(providerService.getSearchedProvider(productionType,searchedProvider).size()==0){
            model.addAttribute("searchedProviderError","No providers matching your search");
        }
        return "streaming-services";
    }


}
