package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CEOMainPageController {

    @GetMapping(value = "/CEO")
    public String ceoMainPage(){
        return "ceoMainPage";
    }
}
