package com.sparta.projectmovie1.movienightplanner.accounthistory;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/history")
public class HistoryWebController {
    HistoryRepository historyRepository;
    HistoryService historyService;

    @Autowired
    public HistoryWebController(HistoryRepository historyRepository, HistoryService historyService) {
        this.historyRepository = historyRepository;
        this.historyService = historyService;
    }

    //Create
    @PostMapping("/create")
    public String createHistoryEntry(@ModelAttribute("historyEntry") HistoryEntry historyEntry, @AuthenticationPrincipal SecurityUser securityUser, HttpServletRequest request){
        String userId = securityUser.getUser().getId();
        historyEntry.setUserId(userId);

        //check if movie is already in history
        if(historyService.isExistingHistoryEntry(historyEntry)) {
            String redirectStr = getPreviousPageByRequest(request).orElse("/");//redirect to previous page
            return redirectStr+"?addToWatchHistoryError=true";
        }

        historyRepository.save(historyEntry);
        //make watch history date calendar between release date and today
        
        //delete from myplanentry if they want


        //REMOVE ACCESS TO API HISTORY!!!!!!

        return "redirect:/history/all";
    }

    //Read
    @GetMapping("/all")
    public String getAllUserHistory(Model model, @AuthenticationPrincipal SecurityUser securityUser){
        model.addAttribute("historyEntries", historyService.getAllUserHistoryByDate(securityUser.getUser().getId()));
        return "history";
    }

    //Update

    //Delete



    //redirect to previous pade
    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

}
