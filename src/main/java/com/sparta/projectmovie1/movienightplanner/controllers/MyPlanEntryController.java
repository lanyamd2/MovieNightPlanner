package com.sparta.projectmovie1.movienightplanner.controllers;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
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

  @Autowired
  public MyPlanEntryController(MyPlanService myPlanService) {
    this.myPlanService = myPlanService;
  }

  @GetMapping("/all")
  public List<MyPlanEntry> getAllEntries() {
    return myPlanService.getAllEntries();
  }

  @PostMapping("/create")
  public MyPlanEntry create(@RequestBody MyPlanEntry myPlanEntry) {
    return myPlanService.addEntry(myPlanEntry);
  }

}
