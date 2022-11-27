package com.example.controllers;

import com.example.models.Project;
import com.example.models.Team;
import com.example.models.User;
import com.example.repositories.ProjectRepository;
import com.example.repositories.TeamRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Controller
public class TeamsController {

    @Autowired
    UserRepository userRepository;

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

    @PostMapping(value = "/teams/addTeam", params = "addTeamForward")
    public String addTeamPage(HttpSession httpSession, Model model){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {

            ArrayList<User> userArrayList = (ArrayList<User>) userRepository.findAll();
            ArrayList<Project> projectArrayList = (ArrayList<Project>) projectRepository.findAll();

            model.addAttribute("users", userArrayList); //Not using this for now
            model.addAttribute("projects", projectArrayList);

            return "addTeam";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/teams/addTeam", params = "cancel")
    public String returnToTeams(HttpSession httpSession){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))){
            return "redirect:/teams";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/teams/addTeam", params = "addTeamButton")
    public String addTeam(HttpSession httpSession, @RequestParam String name
            ,@RequestParam String projects
            //Will be added later after I figure out things
            //,@RequestParam List<String> employeesInTeam
    ){

        try{
            if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {

                if(name.isBlank() || projects.isBlank()){
                    return "redirect:/teams";
                }

                Team team = new Team(name,projectRepository.findByName(projects).get());

                teamRepository.save(team);

                return "redirect:/teams";
            } else {
                return "redirect:/login";
            }

        } catch (NoSuchElementException e){

            return "redirect:/teams";
        }

    }

}
