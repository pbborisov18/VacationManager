package com.example.repositories;

import com.example.models.Role;
import com.example.models.Team;
import com.example.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> getAllByRole(Role role);
    int countByRole(Role role);
    List<User> getAllByTeam(Team team);
    User getUserByLeadDevAndTeam(User user, Team team);
    
}
