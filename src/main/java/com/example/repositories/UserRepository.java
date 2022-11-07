package com.example.repositories;

import com.example.models.Role;
import com.example.models.Team;
import com.example.models.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableAutoConfiguration //Useless but bug in spring boot (prob fixed in later versions)
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> getAllByRole(Role role);
    int countByRole(Role role);
    List<User> getAllByTeam(Team team);
    //User getUserByLeadDevAndTeam(User user, Team team);
    
}
