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
        return "streaming-services";
    }
    @RequestMapping("/providers/tv")
    public String showProvidersForTv(Model model,@AuthenticationPrincipal SecurityUser securityUser){
        String userId = securityUser.getUser().getId();
        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));
        model.addAttribute("tvProviders",providerService.getAllProvidersFromTmdb("tv"));
        return "streaming-services";
    }

    @RequestMapping("/addToProviders")
    public String addToMyProviders(@RequestParam Integer providerId,Model model, @AuthenticationPrincipal SecurityUser securityUser){
        String userId = securityUser.getUser().getId();
        System.out.println("providerId---"+providerId);
        MyProviderEntry myProviderEntry=new MyProviderEntry();
        myProviderEntry.setProviderId(providerId);
        myProviderEntry.setUserId(userId);

        providerService.addToMyProviderEntries(myProviderEntry);


        model.addAttribute("currentProviders",providerService.getCurrentProviders(userId));


        return "streaming-services";


    }
}
