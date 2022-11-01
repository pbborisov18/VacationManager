package com.example.repositories;

import com.example.models.Project;
import com.example.models.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    List<Team> getAllByProject(Project project);
}
