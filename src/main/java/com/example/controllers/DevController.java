package com.example.controllers;

import com.example.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DevController {

    @GetMapping(value = "/Dev")
    public String devMainPage(HttpSession httpSession, Model model){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {
            User user = (User) httpSession.getAttribute("user");

            model.addAttribute("username", user.getUsername());

            return "devMainPage";
        } else {
            return "redirect:/login";
        }
    }

    //Pretty sure that's not how you handle these, but "IT IZZZ WHAT IT IZZZ" (https://www.youtube.com/watch?v=y9r_pZL4boE)
    @PostMapping(value="/Dev", params = "leaves")
    public String leaves(HttpSession httpSession){
        return "devMainPage";
    }

    @PostMapping(value="/Dev", params = "users")
    public String users(HttpSession httpSession){
        return "redirect:/users";
    }

    @PostMapping(value="/Dev", params = "teams")
    public String teams(HttpSession httpSession){
        return "devMainPage";
    }

    @PostMapping(value="/Dev", params = "projects")
    public String projects(HttpSession httpSession){
        return "devMainPage";
    }

    @PostMapping(value="/Dev", params = "logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/login";
    }
}
