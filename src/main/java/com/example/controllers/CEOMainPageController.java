package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CEOMainPageController {

    @GetMapping(value = "/CEO")
    public String ceoMainPage(HttpSession httpSession){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {
            return "ceoMainPage";
        } else {
            return "redirect:/login";
        }
    }

    //Pretty sure that's not how you handle these, but "IT IZZZ WHAT IT IZZZ" (https://www.youtube.com/watch?v=y9r_pZL4boE)
    @PostMapping(value="/CEO", params = "users")
    public String users(HttpSession httpSession){
        return "ceoMainPage";
    }

    @PostMapping(value="/CEO", params = "roles")
    public String roles(HttpSession httpSession){
        return "ceoMainPage";
    }

    @PostMapping(value="/CEO", params = "teams")
    public String teams(HttpSession httpSession){
        return "ceoMainPage";
    }

    @PostMapping(value="/CEO", params = "projects")
    public String projects(HttpSession httpSession){
        return "ceoMainPage";
    }

    @PostMapping(value="/CEO", params = "leaves")
    public String leaves(HttpSession httpSession){
        return "ceoMainPage";
    }

    @PostMapping(value="/CEO", params = "logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/login";
    }

}
