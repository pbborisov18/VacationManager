package com.example.repositories;

import com.example.models.Leave;
import com.example.models.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableAutoConfiguration //Useless but bug in spring boot (prob fixed in later versions)
public interface LeaveRepository extends CrudRepository<Leave, Integer> {
    List<Leave> getAllByUser(User user);
}
