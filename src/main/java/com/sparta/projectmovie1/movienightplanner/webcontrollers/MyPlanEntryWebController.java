package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import com.sparta.projectmovie1.movienightplanner.services.MyPlanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyPlanEntryWebController {

  MyPlanService myPlanService;
  MyPlanEntryRepository myPlanEntryRepository;

  @Autowired
  public MyPlanEntryWebController(MyPlanService myPlanService, MyPlanEntryRepository myPlanEntryRepository) {
    this.myPlanService = myPlanService;
    this.myPlanEntryRepository = myPlanEntryRepository;
  }

//  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping("/myplan")
  public String getAllProductionsInPlan(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
    model.addAttribute("entriesWithDates", myPlanService.getAllProductionsWithDatesInPlan(securityUser.getUser().getId()));
    return "my-plan";
  }

//  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping("/myplan/date")
  public String getMoviesOnDate(Model model, @RequestParam String date, @AuthenticationPrincipal SecurityUser securityUser) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date formattedDate = null;
    try {
      formattedDate = formatter.parse(date);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("productions", myPlanService.getProductionsOnDate(securityUser.getUser().getId(), formattedDate));
    model.addAttribute("date", formattedDate);
    return "my-plan-date";
  }

//  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping("/myplan/create")
  public String createEntry(@ModelAttribute("myPlanEntry") MyPlanEntry myPlanEntry, @AuthenticationPrincipal SecurityUser securityUser,
                            Model model) {
    String userId = securityUser.getUser().getId();
    myPlanEntry.setUserId(userId);
    myPlanEntryRepository.save(myPlanEntry);
    model.addAttribute("entriesWithDates", myPlanService.getAllProductionsWithDatesInPlan(userId));
    return "my-plan";
  }

  @DeleteMapping("/myplan/delete")
  public String deleteEntry(@RequestParam Integer productionId, @RequestParam Date date, @AuthenticationPrincipal SecurityUser securityUser, Model model) {
    String userId = securityUser.getUser().getId();
    myPlanService.deleteEntry(userId, productionId, date);
    model.addAttribute("entriesWithDates", myPlanService.getAllProductionsWithDatesInPlan(userId));
    return "my-plan";
  }


//  @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("addtoplan/{productionType}")
    public String addToMyplan(@PathVariable String productionType, MyPlanEntry myPlanEntry, Model model, @AuthenticationPrincipal SecurityUser securityUser){

        if(productionType.equals("movie")){
          myPlanEntry.setMovie(true);
        }
        String userId = securityUser.getUser().getId();
        myPlanEntry.setUserId(userId);
        myPlanEntryRepository.save(myPlanEntry);
        model.addAttribute("entriesWithDates", myPlanService.getAllProductionsWithDatesInPlan(userId));
        return "my-plan";


    }

}
