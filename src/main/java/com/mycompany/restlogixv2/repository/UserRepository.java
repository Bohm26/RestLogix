package com.mycompany.restlogixv2.repository;

import com.mycompany.restlogixv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
