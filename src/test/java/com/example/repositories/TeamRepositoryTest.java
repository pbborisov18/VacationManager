package com.example.repositories;

import com.example.models.Project;
import com.example.models.Team;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    Project project1;
    Project project2;

    @BeforeAll
    void beforeAll(){
        project1 = new Project("TestProject1", "For Testing!");
        project2 = new Project("TestProject2","For Testing!");

        projectRepository.save(project1);
        projectRepository.save(project2);
    }

    @Test
    void getAllByProject(){
        //Given
            Team team1 = new Team("Team1",project1);
            Team team2 = new Team("Team2", project1);
            Team team3 = new Team("Team3", project2);
        //When
            teamRepository.save(team1);
            teamRepository.save(team2);
            teamRepository.save(team3);
        //Then
            assertThat(teamRepository.getAllByProject(project1)).isEqualTo(Arrays.asList(team1,team2));
            assertThat(teamRepository.getAllByProject(project2)).isEqualTo(Arrays.asList(team3));
    }
}
