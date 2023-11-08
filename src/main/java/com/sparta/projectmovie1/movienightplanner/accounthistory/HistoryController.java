package com.sparta.projectmovie1.movienightplanner.accounthistory;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HistoryController {
    HistoryRepository historyRepository;
    HistoryService historyService;

    @Autowired
    public HistoryController(HistoryRepository historyRepository, HistoryService historyService) {
        this.historyRepository = historyRepository;
        this.historyService = historyService;
    }

    //Create
    @PostMapping("/history/create")
    public String createHistoryEntry(@ModelAttribute("historyEntry") HistoryEntry historyEntry, @AuthenticationPrincipal SecurityUser securityUser, Model model){
        String userId = securityUser.getUser().getId();
        historyEntry.setUserId(userId);

        //check if movie is already in history
        if(historyService.isExistingHistoryEntry(historyEntry)) {
            throw new HistoryEntryAlreadyExistsException("Already added to your watch history");//change to a flashattribute that appears on form as error message
        }
        historyRepository.save(historyEntry);
        //make watch history date between release date and today
        //delete from myplanentry

        //get all history entries for user method in chronological order MOST RECENT first
        //add these history entries to the model
        return "redirect:/index";//CHANGE TO HISTORY PAGE WHEN CREATED
    }

    //Read - use hateoas link to each production

    //Update

    //Delete

}
