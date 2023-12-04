package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import com.sparta.projectmovie1.movienightplanner.models.users.User;
import com.sparta.projectmovie1.movienightplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@ControllerAdvice
public class UserToViewControllerAdvice {

    UserService userService;

    @Autowired
    public UserToViewControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("signedInUser")
    public User addUserToModel(Authentication authentication){
        if(authentication != null){
            SecurityUser securityUser= (SecurityUser) authentication.getPrincipal();
//            System.out.println("FROM SECURITY USER: " +securityUser.getUser());
            Optional<User> user = userService.findByUsername(securityUser.getUser().getUsername());
            if(user.isEmpty()){
                throw new UsernameNotFoundException("username not found from security user");
            }

//            System.out.println("FROM DATABASE: "+ user.get());
            return user.get();
        }
        else{
            return null;
        }
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String usernameNotFoundHandler(UsernameNotFoundException e){
        return e.getMessage();
    }
}
