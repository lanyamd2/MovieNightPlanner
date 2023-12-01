package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import com.sparta.projectmovie1.movienightplanner.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/theme", method = RequestMethod.POST)
    public String setUserTheme(@RequestParam(value="toggleDarkName", required=false) String toggleDarkValue, @AuthenticationPrincipal SecurityUser securityUser, HttpServletRequest request){
        if(toggleDarkValue!=null && toggleDarkValue.equals("on")){
            //set isDark to true
            userService.updateUserTheme(securityUser.getUser(), true);

        }else{
            //set isDark to false
            userService.updateUserTheme(securityUser.getUser(), false);
        }

//        System.out.println("in setUserTheme : "+toggleDarkValue);

        String referrerURL=request.getHeader("Referer");

            return "redirect:"+request.getHeader("Referer");
        

    }

}
