package com.sparta.projectmovie1.movienightplanner.accounthistory;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        
        //delete from myplanentry if they want
        return "redirect:/history/all";
    }

    //Read
    @GetMapping("/all")
    public String getAllUserHistory(@RequestParam(required = false) String historyEntryDeleteError,@RequestParam(required = false) String editWatchHistoryError,@RequestParam(required = false) String existingEntryNotFoundError, Model model, @AuthenticationPrincipal SecurityUser securityUser){
        if(historyEntryDeleteError!=null){
            model.addAttribute("historyEntryDeleteError","This history entry could not be deleted.");
        }
        if(editWatchHistoryError!=null){//if edi
            model.addAttribute("editWatchHistoryError","You have already added this production to your history on that date.");
        }
        if(existingEntryNotFoundError!=null){//if edi
            model.addAttribute("existingEntryNotFoundError","This history entry could not be edited.");
        }

        List<String> historyIds = historyService.getAllHistoryEntriesByUserId(securityUser.getUser().getId()).stream().map(HistoryEntry::getId).toList();
        model.addAttribute("historyIds", historyIds);

        model.addAttribute("historyEntryEdit", new HistoryEntry());
        model.addAttribute("historyEntries", historyService.getAllUserHistoryByDate(securityUser.getUser().getId()));
        return "history";
    }

    //Update
    @PatchMapping("/edit")
    public String editHistoryEntry(@ModelAttribute("historyEntryEdit")HistoryEntry historyEntryEdit){
        Optional<HistoryEntry> existingEntry = historyRepository.findHistoryEntryById(historyEntryEdit.getId());

        if(existingEntry.isEmpty()){
            return "redirect:/history/all?existingEntryNotFoundError=true";
        }

        existingEntry.get().setDate(historyEntryEdit.getDate());

        if(historyService.isExistingHistoryEntry(existingEntry.get())){
            return "redirect:/history/all?editWatchHistoryError=true";
        }

        historyRepository.save(existingEntry.get());
        return "redirect:/history/all";
    }


    //Delete
    @GetMapping("/delete/{historyEntryId}")
    String deleteHistoryEntryByHistoryEntryId(@PathVariable("historyEntryId")String id, HttpServletRequest request){
        Optional<HistoryEntry> historyEntryOptional = historyRepository.findHistoryEntryById(id);
        if(historyEntryOptional.isPresent()){
            historyRepository.deleteById(id); //successful delete
            return"redirect:/history/all";
        }

        //redirects to user's full history page with error
        return "redirect:/history/all?historyEntryError=true";

    }



    //redirect to previous pade
    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

}
