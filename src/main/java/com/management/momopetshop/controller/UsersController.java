package com.management.momopetshop.controller;


import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.management.momopetshop.model.Users;
import com.management.momopetshop.service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    
    // ✅ GET ALL USERS
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    // GET user by ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return usersService.getUserById(id);
    }

    // GET user by username
    @GetMapping("/username/{username}")
    public Users getUserByUsername(@PathVariable String username) {
        return usersService.getUserByUsername(username);
    }

    // POST create new user
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }

    // PUT update user
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Integer id, @RequestBody Users user) {
        user.setIdUser(id);
        return usersService.saveUser(user);
    }
}

