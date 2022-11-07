package com.example.repositories;

import com.example.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LeaveRepositoryTest {

    @Mock
    User user;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LeaveRepository leaveRepository;

    @BeforeAll
    static void beforeAll(){

    }

    @Test
    void getAllByUser(){
        //Given

        //When

        //Then

    }

}
