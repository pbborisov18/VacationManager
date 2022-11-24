package com.example.repositories;

import com.example.models.Project;
import com.example.models.Team;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableAutoConfiguration //Useless but bug in spring boot (prob fixed in later versions)
public interface TeamRepository extends CrudRepository<Team, Integer> {
    Optional<Team> findTeamByName(String name);
    List<Team> getAllByProject(Project project);
}
