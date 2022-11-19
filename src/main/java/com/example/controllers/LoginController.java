package com.example.controllers;



import com.example.models.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.TeamRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TeamRepository teamRepository;

    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String mainPage(@RequestParam String username, @RequestParam String password, HttpSession httpSession){

        User user;

        if(userRepository.findByUsername(username).isPresent()){
            user = userRepository.findByUsername(username).get();
        } else {
            //Return some kind of error or popup informing the user the credentials aren't correct
            return "login";
        }

        httpSession.setAttribute("user", user);
        httpSession.setAttribute("sessionId", httpSession.getId());

        if(user.getUsername().equals(username) && BCrypt.checkpw(password, user.getPassword())) {
            if(user.getRole().getRoleName().equals("TeamLead")){
               return "redirect:/TeamLead";
            } else if(user.getRole().getRoleName().equals("CEO")) {
                return "redirect:/CEO";
            } else {
                return "redirect:/Dev";
            }
        }

        return null;
    }

//    private void saveForLater(){
//
//        String password = "a";
//
//        SecureRandom secureRandom = new SecureRandom();
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10,secureRandom);
//
//        User user = new User("Test", bCryptPasswordEncoder.encode(password), "Ivan", "Mitkov",
//                roleRepository.findRoleByRoleName("Developer").get(),teamRepository.findById(1).get(),true);
//
//        userRepository.save(user);
//    }
}
