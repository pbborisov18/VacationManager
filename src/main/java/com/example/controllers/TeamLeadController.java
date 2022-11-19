package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TeamLeadController {

    @GetMapping(value = "/TeamLead")
    public String teamLeadMainPage(HttpSession httpSession){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {
            return "teamLeadMainPage";
        } else {
            return "redirect:/login";
        }
    }

    //Pretty sure that's not how you handle these, but "IT IZZZ WHAT IT IZZZ" (https://www.youtube.com/watch?v=y9r_pZL4boE)
    @PostMapping(value="/TeamLead", params = "leaves")
    public String roles(HttpSession httpSession){
        return "teamLeadMainPage";
    }

    @PostMapping(value="/TeamLead", params = "users")
    public String users(HttpSession httpSession){
        return "teamLeadMainPage";
    }

    @PostMapping(value="/TeamLead", params = "teams")
    public String teams(HttpSession httpSession){
        return "teamLeadMainPage";
    }

    @PostMapping(value="/TeamLead", params = "projects")
    public String projects(HttpSession httpSession){
        return "teamLeadMainPage";
    }

    @PostMapping(value="/TeamLead", params = "logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/login";
    }
}
