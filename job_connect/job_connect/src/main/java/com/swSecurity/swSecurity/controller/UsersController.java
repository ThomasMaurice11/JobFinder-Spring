package com.swSecurity.swSecurity.controller;

// import com.swSecurity.swSecurity.model.entity.User;
import com.swSecurity.swSecurity.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swSecurity.swSecurity.user.User;
// import com.swSecurity.swSecurity.model.entity.User;
// import com.swSecurity.swSecurity.model.repository.UsersRepository;
import com.swSecurity.swSecurity.user.UserRepo;

import java.util.List;

@RestController
@RequestMapping(path = "jobConnect/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId){
        return usersService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        return usersService.createUser(user);
    }
}
