package com.sparta.projectmovie1.movienightplanner.controllers;

import com.sparta.projectmovie1.movienightplanner.models.users.User;
import com.sparta.projectmovie1.movienightplanner.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userBindingResult", new BeanPropertyBindingResult(user, "user"));

        return "registration";
    }


    @PostMapping("/registration")
    public String register(@ModelAttribute @Valid User user, BindingResult bindingResult,  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        user.setRoles("ROLE_USER");

        userRepository.save(user);
        redirectAttributes.addAttribute("successMessage", "Registration successful!");
        return "redirect:/login";
    }
}

