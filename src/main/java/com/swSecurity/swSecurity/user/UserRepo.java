package com.swSecurity.swSecurity.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    //find user by email this return object of user with his details
    Optional<User> findByEmail(String email);
}
