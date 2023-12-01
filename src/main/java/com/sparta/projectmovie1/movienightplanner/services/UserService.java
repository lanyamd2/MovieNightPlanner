package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.users.User;
import com.sparta.projectmovie1.movienightplanner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user){
        user.setRoles("ROLE_USER");
        user.setDarkMode(false);
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean isExistingUsername(String username){
        return findByUsername(username).isPresent();
    }

    public boolean isExistingEmail(String email){
        return findByEmail(email).isPresent();
    }

    public boolean isValidPassword(String password){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=!*()-])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);

        if(password==null){
            return false;
        }

        Matcher m = p.matcher(password);
        return m.matches();
    }

    public void updateUserTheme(User user, boolean desiredIsDark){
        if(isExistingUsername(user.getUsername())){
            user.setDarkMode(desiredIsDark);
            System.out.println("FROM USER SERVICE: "+user);
            userRepository.save(user);
        }else{
            throw new UsernameNotFoundException(user.getUsername()+"not found in database");
        }
    }

}
