package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamLeadController {

    @GetMapping(value = "/TeamLead")
    public String teamLeadMainPage(){
        return "teamLeadMainPage";
    }
}
