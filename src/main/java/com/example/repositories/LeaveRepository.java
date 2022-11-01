package com.example.repositories;

import com.example.models.Leave;
import com.example.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveRepository extends CrudRepository<Leave, Integer> {
    List<Leave> getAllByUser(User user);
}
