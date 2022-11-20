package com.example.controllers;

import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/users")
    public String usersMainPage(HttpSession httpSession, Model model){

        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {

            User user = (User) httpSession.getAttribute("user");

            List userList = (List) userRepository.findAll();
            model.addAttribute("users", userList);

            if(user.isLeadDev()
                    || user.getRole().getName().equals("CEO")
                    || user.getRole().getName().equals("TeamLead")) {
                return "usersAllPrivileges";
            } else {
                return "users";
            }

        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/users/back", params = "back")
    public String back(HttpSession httpSession){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))){
            User user = (User) httpSession.getAttribute("user");

            if(user.getRole().getName().equals("CEO")){
                return "redirect:/CEO";
            } else if(user.getRole().getName().equals("TeamLead")){
                return "redirect:/TeamLead";
            } else {
                return "redirect:/Dev";
            }
        }

        return "redirect:/login";

    }

}
