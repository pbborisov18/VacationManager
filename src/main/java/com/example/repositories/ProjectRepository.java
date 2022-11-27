package com.example.repositories;

import com.example.models.Project;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableAutoConfiguration //Useless but bug in spring boot (prob fixed in later versions)
public interface ProjectRepository extends CrudRepository<Project, Integer> {
    public Optional<Project> findByName(String name);
}
