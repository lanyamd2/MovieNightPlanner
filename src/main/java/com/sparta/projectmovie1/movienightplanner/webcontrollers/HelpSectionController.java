package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpSectionController {
  @GetMapping("/help")
  public String showHelpPage() {
    return "help";
  }
}
