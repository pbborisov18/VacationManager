package com.example.repositories;

import com.example.models.Project;
import com.example.models.Role;
import com.example.models.Team;
import com.example.models.User;
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
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    Role role1;
    Role role2;
    Team team1;
    Team team2;

    @BeforeAll
    void beforeAll(){
        role1 = new Role("Role Test1");
        role2 = new Role("Role Test2");

        Project project = new Project("ProjectName","Test Project");
        team1 = new Team("Team Name1", project);
        team2 = new Team("Team Name2", project);

        roleRepository.save(role1);
        roleRepository.save(role2);

        projectRepository.save(project);
        teamRepository.save(team1);
        teamRepository.save(team2);

    }

    @Test
    void getAllByRoleTest() {
        //Given
        User user1 = new User("user1","pass1", "123".getBytes(), "fName1", "lName1", role1, team1, false);
        User user2 = new User("user2","pass2", "123".getBytes(), "fName2", "lName2", role1, team1, false);
        User user3 = new User("user3","pass3", "123".getBytes(), "fName3", "lName3", role1, team1, false);

        User user4 = new User("user4","pass4", "123".getBytes(), "fName4", "lName4", role2, team1, false);
        User user5 = new User("user5","pass5", "123".getBytes(), "fName5", "lName5", role2, team1, false);

        //When
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        //Then
        assertThat(userRepository.getAllByRole(role1)).isEqualTo(Arrays.asList(user1,user2,user3));
        assertThat(userRepository.getAllByRole(role2)).isEqualTo(Arrays.asList(user4,user5));
    }

    @Test
    void countByRoleTest() {
        //Given
        User user1 = new User("user1","pass1", "123".getBytes(), "fName1", "lName1", role1, team1, false);
        User user2 = new User("user2","pass2", "123".getBytes(), "fName2", "lName2", role1, team1, false);
        User user3 = new User("user3","pass3", "123".getBytes(), "fName3", "lName3", role1, team1, false);

        User user4 = new User("user4","pass4", "123".getBytes(), "fName4", "lName4", role2, team1, false);
        User user5 = new User("user5","pass5", "123".getBytes(), "fName5", "lName5", role2, team1, false);

        //When
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        //Then
        assertThat(userRepository.countByRole(role1)).isEqualTo(3);
        assertThat(userRepository.countByRole(role2)).isEqualTo(2);

    }

    @Test
    void getAllByTeamTest() {
        //Given
        User user1 = new User("user1","pass1", "123".getBytes(), "fName1", "lName1", role1, team1, false);
        User user2 = new User("user2","pass2", "123".getBytes(), "fName2", "lName2", role1, team1, false);
        User user3 = new User("user3","pass3", "123".getBytes(), "fName3", "lName3", role1, team1, false);

        User user4 = new User("user4","pass4", "123".getBytes(), "fName4", "lName4", role1, team2, false);
        User user5 = new User("user5","pass5", "123".getBytes(), "fName5", "lName5", role1, team2, false);

        //When
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        //Then
        assertThat(userRepository.getAllByTeam(team1)).isEqualTo(Arrays.asList(user1,user2,user3));
        assertThat(userRepository.getAllByTeam(team2)).isEqualTo(Arrays.asList(user4,user5));
    }

    @Test
    void getUserByTeamAndLeadDevIsTrueTest(){
        //Given
        User user1 = new User("user1","pass1", "123".getBytes(), "fName1", "lName1", role1, team1, true);
        User user2 = new User("user2","pass2", "123".getBytes(), "fName2", "lName2", role1, team1, false);
        User user3 = new User("user3","pass3", "123".getBytes(), "fName3", "lName3", role1, team1, false);

        User user4 = new User("user4","pass4", "123".getBytes(), "fName4", "lName4", role1, team2, true);
        User user5 = new User("user5","pass5", "123".getBytes(), "fName5", "lName5", role1, team2, false);

        //When
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        //Then
        assertThat(userRepository.getUserByTeamAndLeadDevIsTrue(team1)).isEqualTo(user1);
        assertThat(userRepository.getUserByTeamAndLeadDevIsTrue(team2)).isEqualTo(user4);
    }

}