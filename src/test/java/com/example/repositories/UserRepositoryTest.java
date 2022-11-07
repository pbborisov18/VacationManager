package com.example.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Test
    void getAllByRoleTest() {
        //Given

        //When

        //Then

    }

    @Test
    void countByRoleTest() {
        //Given

        //When

        //Then

    }

    @Test
    void getAllByTeamTest() {
        //Given

        //When

        //Then

    }
}