package com.example.controllers;

import com.example.models.Role;
import com.example.models.Team;
import com.example.models.User;
import com.example.repositories.ProjectRepository;
import com.example.repositories.RoleRepository;
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
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping(value = "/users")
    public String usersMainPage(HttpSession httpSession, Model model){

        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {

            User user = (User) httpSession.getAttribute("user");

            ArrayList<User> userList = (ArrayList<User>) userRepository.findAll();
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

    @PostMapping(value = "/users/addUser", params = "addUser")
    public String addUserPage(HttpSession httpSession, Model model){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {

            ArrayList<Role> roleList = (ArrayList<Role>) roleRepository.findAll();
            ArrayList<Team> teamList = (ArrayList<Team>) teamRepository.findAll();

            model.addAttribute("roles", roleList);
            model.addAttribute("teams", teamList);

            return "addUser";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/users/addUser", params = "cancel")
    public String returnToUserList(HttpSession httpSession){
        if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))){
            return "redirect:/users";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/users/addUser", params = "addUserButton")
    public String addUser(HttpSession httpSession, @RequestParam String username
            ,@RequestParam String password
            ,@RequestParam String firstName
            ,@RequestParam String lastName
            ,@RequestParam String roles
            ,@RequestParam String teams
            ,@RequestParam Boolean leadDev){

        try{
            if(httpSession.getId().equals(httpSession.getAttribute("sessionId"))) {
                User user = new User(username, password, firstName, lastName, roleRepository.findRoleByName(roles).get(),
                        teamRepository.findTeamByName(teams).get(), leadDev);

                userRepository.save(user);

                return "redirect:/users";
            } else {
                return "redirect:/login";
            }

        } catch (NoSuchElementException e){

            return "redirect:/login";
        }

    }

}
