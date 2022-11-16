package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DevController {

    @GetMapping(value = "/Dev")
    public String devMainPage(){
        return "devMainPage";
    }
}
