package com.sparta.projectmovie1.movienightplanner.controllers;

import com.sparta.projectmovie1.movienightplanner.models.TrialUser;
import com.sparta.projectmovie1.movienightplanner.repositories.TrialUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrialUserController {

    @Autowired
    TrialUserRepo trialUserRepo;

    @GetMapping("/trial-user")
    public List<TrialUser> getAllTrialUsers(){
        return trialUserRepo.findAll();
    }

    @PostMapping("/trial-user")
    public TrialUser addNewTrialUser(@RequestBody TrialUser user){
        return trialUserRepo.save(user);
    }
}
