package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPlanEntryWebController {

  MyPlanEntryRepository myPlanEntryRepository;

  @Autowired
  public MyPlanEntryWebController(MyPlanEntryRepository myPlanEntryRepository) {
    this.myPlanEntryRepository = myPlanEntryRepository;
  }

}
