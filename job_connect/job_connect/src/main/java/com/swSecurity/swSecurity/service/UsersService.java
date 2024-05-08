package com.swSecurity.swSecurity.service;

import com.swSecurity.swSecurity.user.User;
// import com.swSecurity.swSecurity.model.entity.User;
// import com.swSecurity.swSecurity.model.repository.UsersRepository;
import com.swSecurity.swSecurity.user.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService {

    private final UserRepo usersRepository;

    @Autowired
    public UsersService(UserRepo usersRepository) {
        this.usersRepository = usersRepository;
    }


    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }


    public ResponseEntity<User> getUserById(int userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(()-> new IllegalStateException(
                        "job with id " + userId + " not found"));
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> createUser(User user) {
        User createdUser = usersRepository.save(user);
        return ResponseEntity.ok(createdUser);
    }
}
