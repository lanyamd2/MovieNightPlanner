package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.models.users.User;
import com.sparta.projectmovie1.movienightplanner.repositories.UserRepository;
import com.sparta.projectmovie1.movienightplanner.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private UserService userService;

    @Autowired
    public RegistrationController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {

        if(!model.containsAttribute("user")){
            User user = new User();
            model.addAttribute("user", user);
            model.addAttribute("userBindingResult", new BeanPropertyBindingResult(user, "user"));
        }

        return "registration";
    }


    @PostMapping("/registration")
    public String register(@ModelAttribute @Valid User user, BindingResult bindingResult,  RedirectAttributes redirectAttributes) {

        if(userService.isExistingUsername(user.getUsername())){
            bindingResult.addError(new FieldError("user","username", "Username is already taken"));
        }

        if(userService.isExistingEmail(user.getEmail())){
            bindingResult.addError(new FieldError("user","email","There's already an account for this email"));
        }

        if(!userService.isValidPassword(user.getPassword())){
            bindingResult.addError(new FieldError("user", "password", "Password must be 8 - 20 characters, contain at least one digit, one upper and lower case character, one special character e.g., !@#$%&*()-+=^ and cannot contain spaces!"));
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userBindingResult", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);

            return "registration";
        }


        userService.registerUser(user);

        //redirectAttributes.addAttribute("successMessage", "Registration successful!");
        return "redirect:/login";
    }
}

