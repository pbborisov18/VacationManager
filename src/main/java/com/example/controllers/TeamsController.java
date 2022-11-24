package com.example.controllers;

import com.example.models.Project;
import com.example.models.Team;
import com.example.models.User;
import com.example.repositories.ProjectRepository;
import com.example.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class TeamsController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping(value = "/teams")
    public String teamsMainPage(HttpSession httpSession, Model model){

        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {
            User user = (User) httpSession.getAttribute("user");

            ArrayList<Team> teamArrayList = (ArrayList<Team>) teamRepository.findAll();
            model.addAttribute("teams", teamArrayList);

            if(user.isLeadDev()
                    || user.getRole().getName().equals("CEO")
                    || user.getRole().getName().equals("TeamLead")) {
                return "teamsAllPrivileges";
            } else {
                return "teams";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/teams/back", params = "back")
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
