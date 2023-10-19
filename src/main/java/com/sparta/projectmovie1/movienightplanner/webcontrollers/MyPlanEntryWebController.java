package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.services.MyPlanService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyPlanEntryWebController {

  MyPlanService myPlanService;

  @Autowired
  public MyPlanEntryWebController(MyPlanService myPlanService) {
    this.myPlanService = myPlanService;
  }

  @GetMapping("/myplan")
  public String getAllProductionsInPlan(Model model) {
    model.addAttribute("productions", myPlanService.getAllProductionsInPlan());
    return "my-plan";
  }

  @GetMapping("/myplan/date")
  public String getMoviesOnDate(Model model, @RequestParam String date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date formattedDate = null;
    try {
      formattedDate = formatter.parse(date);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("productions", myPlanService.getProductionsOnDate(formattedDate));
    return "my-plan-date";
  }


}
