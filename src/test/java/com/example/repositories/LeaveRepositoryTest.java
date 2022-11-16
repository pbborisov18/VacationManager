package com.example.repositories;


import com.example.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LeaveRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LeaveRepository leaveRepository;

    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    @BeforeAll
    void beforeAll(){
        Role role = new Role("TestRole");
        Project project = new Project("TestProject","For Testing");
        Team team = new Team("TestTeam", project);

        roleRepository.save(role);
        projectRepository.save(project);
        teamRepository.save(team);

        user1 = new User("user1","pass1",  "fName1", "lName1", role, team, false);
        user2 = new User("user2","pass2",  "fName2", "lName2", role, team, false);
        user3 = new User("user3","pass3",  "fName3", "lName3", role, team, false);
        user4 = new User("user4","pass4",  "fName4", "lName4", role, team, false);
        user5 = new User("user5","pass5",  "fName5", "lName5", role, team, false);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
    }


    @Test
    void getAllByUserTest(){
        //Given
        Leave leave1 = new Leave(LocalDateTime.of(2022,5,25, 11, 40),
                LocalDateTime.of(2022,5,27,9,0),
                LocalDateTime.of(2022,5,15,15,20),
                false, false, user1);
        Leave leave2 = new Leave(LocalDateTime.of(2022,5,29, 11, 40),
                LocalDateTime.of(2022,5,30,9,0),
                LocalDateTime.of(2022,5,15,15,25),
                false, false, user1);

        //When
            leaveRepository.save(leave1);
            leaveRepository.save(leave2);
        //Then
            assertThat(leaveRepository.getAllByUser(user1)).isEqualTo(Arrays.asList(leave1,leave2));
    }

    @Test
    void getAllByUserButMoreThanOneUserInTest(){
        //Given
        Leave leave1 = new Leave(LocalDateTime.of(2022,5,25, 11, 40),
                LocalDateTime.of(2022,5,27,9,0),
                LocalDateTime.of(2022,5,15,15,20),
                false, false, user1);
        Leave leave2 = new Leave(LocalDateTime.of(2022,5,29, 11, 40),
                LocalDateTime.of(2022,5,30,9,0),
                LocalDateTime.of(2022,5,15,15,25),
                false, false, user1);
        Leave leave3 = new Leave(LocalDateTime.of(2022,5,29, 11, 40),
                LocalDateTime.of(2022,5,30,9,0),
                LocalDateTime.of(2022,5,15,15,25),
                false, false, user2);
        Leave leave4 = new Leave(LocalDateTime.of(2022,5,29, 11, 40),
                LocalDateTime.of(2022,5,30,9,0),
                LocalDateTime.of(2022,5,15,15,25),
                false, false, user3);

        //When
        leaveRepository.save(leave1);
        leaveRepository.save(leave2);
        leaveRepository.save(leave3);
        leaveRepository.save(leave4);

        //Then
        assertThat(leaveRepository.getAllByUser(user1)).isEqualTo(Arrays.asList(leave1,leave2));

        assertThat(leaveRepository.getAllByUser(user2)).isEqualTo(Arrays.asList(leave3));

        assertThat(leaveRepository.getAllByUser(user3)).isEqualTo(Arrays.asList(leave4));
    }

}
