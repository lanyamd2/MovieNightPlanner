package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.services.HelpSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpSectionController {
  private final HelpSectionService helpSectionService;

  @Autowired
  public HelpSectionController(HelpSectionService helpSectionService) {
    this.helpSectionService = helpSectionService;
  }

  @GetMapping("/help")
  public String showHelpPage(Model model) {
    model.addAttribute("providers", helpSectionService.getProviders());
    return "help";
  }
}
