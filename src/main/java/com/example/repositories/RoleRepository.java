package com.example.repositories;

import com.example.models.Role;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableAutoConfiguration //Useless but bug in spring boot (prob fixed in later versions)
public interface RoleRepository extends CrudRepository<Role, Integer>{
    Optional<Role> findRoleByName(String roleName);
}
