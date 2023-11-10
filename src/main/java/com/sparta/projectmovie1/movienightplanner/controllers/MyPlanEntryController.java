package com.sparta.projectmovie1.movienightplanner.controllers;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import com.sparta.projectmovie1.movienightplanner.services.MyPlanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myplan")
public class MyPlanEntryController {

  private final MyPlanService myPlanService;
  private final MyPlanEntryRepository myPlanEntryRepository;

  @Autowired
  public MyPlanEntryController(MyPlanService myPlanService,
      MyPlanEntryRepository myPlanEntryRepository) {
    this.myPlanService = myPlanService;
    this.myPlanEntryRepository = myPlanEntryRepository;
  }

  @PostMapping("/create")
  public MyPlanEntry create(@RequestBody MyPlanEntry myPlanEntry) {
    return myPlanEntryRepository.save(myPlanEntry);
  }

}
