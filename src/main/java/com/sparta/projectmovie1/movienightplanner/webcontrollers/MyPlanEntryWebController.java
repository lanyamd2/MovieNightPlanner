package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import com.sparta.projectmovie1.movienightplanner.services.MyPlanService;

import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MyPlanEntryWebController {

  MyPlanService myPlanService;
  MyPlanEntryRepository myPlanEntryRepository;

  @Autowired
  public MyPlanEntryWebController(MyPlanService myPlanService, MyPlanEntryRepository myPlanEntryRepository) {
    this.myPlanService = myPlanService;
    this.myPlanEntryRepository = myPlanEntryRepository;
  }

  @GetMapping("/myplan")
  public String getAllProductionsInPlan(Model model) {
    model.addAttribute("productions", myPlanService.getAllProductionsInPlan());
    return "my-plan";
  }

  @GetMapping("/myplan/date")
  public String getMoviesOnDate(Model model, @RequestParam String date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date formattedDate = null;
    try {
      formattedDate = formatter.parse(date);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    model.addAttribute("productions", myPlanService.getProductionsOnDate(formattedDate));
    return "my-plan-date";
  }

  @PostMapping("/myplan/create")
  public String createEntry(@ModelAttribute("myPlanEntry") MyPlanEntry myPlanEntry,
                            Model model) {
    myPlanEntryRepository.save(myPlanEntry);
    model.addAttribute("productions", myPlanService.getAllProductionsInPlan());
    return "my-plan";
  }


  @RequestMapping("addtoplan/{productionType}/{productionId}")
  public String addToMyplan(@PathVariable String productionType, @PathVariable Integer productionId, MyPlanEntry myPlanEntry, Model model){

    System.out.println("Production Id from path variable ---->"+productionId);
    System.out.println("Production Id from myPlanEntry object ---->"+myPlanEntry.getProductionId());
    MyPlanEntry thePlanEntry=new MyPlanEntry();
    thePlanEntry.setProductionId(productionId);
    if(productionType.equals("movie")){
      thePlanEntry.setMovie(true);
    }
    else{
      thePlanEntry.setMovie(false);
    }
    thePlanEntry.setDate(myPlanEntry.getDate());
    System.out.println(thePlanEntry.getProductionId()+"-----"+thePlanEntry.isMovie()+"----"+thePlanEntry.getDate());
    myPlanEntryRepository.save(thePlanEntry);
    System.out.println(myPlanEntry.getDate());
    System.out.println("Before adding the modal attribute");
    model.addAttribute("productions", myPlanService.getAllProductionsInPlan());
    return "my-plan";


  }

    @RequestMapping("addtoplan/{productionType}")
    public String addToMyplan(@PathVariable String productionType, MyPlanEntry myPlanEntry, Model model,@RequestParam String date){

        if(productionType.equals("movie")){
          myPlanEntry.setMovie(true);
        }
        System.out.println("Date---->"+date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date formattedDate = null;
        try {
        formattedDate = formatter.parse(date);
        } catch (ParseException e) {
        throw new RuntimeException(e);
      }
        myPlanEntry.setDate(formattedDate);
        myPlanEntryRepository.save(myPlanEntry);
        model.addAttribute("productions", myPlanService.getAllProductionsInPlan());
        return "my-plan";


    }

}
