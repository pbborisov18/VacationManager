package com.example.repositories;

import com.example.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByRoleNameTest(){
        //Given
        Role role = new Role("Test Role");

        //When
        roleRepository.save(role);

        //Then
        assertThat(roleRepository.findRoleByRoleName(role.getRoleName()).get())
                .isEqualTo(role);
    }

    @Test
    void findByRoleNameButTableIsEmptyTest(){
        //Given
        Role role = new Role("Test Role");
        //When

        //Then
        assertThat(roleRepository.findRoleByRoleName(role.getRoleName()).isEmpty()).isTrue();
    }

    @Test
    void findByRoleNameButWrongRoleTest(){
        //Given
        Role correctRole = new Role("Correct Role");
        Role wrongRole = new Role("Wrong Role");

        //When
        roleRepository.save(correctRole);

        //Then
        assertThat(roleRepository.findRoleByRoleName(wrongRole.getRoleName()).isEmpty())
                .isTrue()
                .isNotEqualTo(correctRole);
    }

}
