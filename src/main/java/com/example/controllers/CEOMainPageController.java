package com.example.controllers;

import com.example.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CEOMainPageController {

    @GetMapping(value = "/CEO")
    public String ceoMainPage(HttpSession httpSession, Model model){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {
            User user = (User) httpSession.getAttribute("user");

            model.addAttribute("username", user.getUsername());

            return "ceoMainPage";
        } else {
            return "redirect:/login";
        }
    }

    //Pretty sure that's not how you handle these, but "IT IZZZ WHAT IT IZZZ" (https://www.youtube.com/watch?v=y9r_pZL4boE)
    @PostMapping(value="/CEO", params = "users")
    public String users(HttpSession httpSession){return "redirect:/users";}

    @PostMapping(value="/CEO", params = "roles")
    public String roles(HttpSession httpSession){
        return "ceoMainPage";
    }

    @PostMapping(value="/CEO", params = "teams")
    public String teams(HttpSession httpSession){
        return "redirect:/teams";
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
