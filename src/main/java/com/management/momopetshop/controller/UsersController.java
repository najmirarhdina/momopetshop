package com.management.momopetshop.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

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

    // =============================
    // GET ALL USERS
    // =============================
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return usersService.getUserById(id);
    }

    // GET USER BY USERNAME
    @GetMapping("/username/{username}")
    public Users getUserByUsername(@PathVariable String username) {
        return usersService.getUserByUsername(username);
    }

    // CREATE USER
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }

    // =================================================
    // ✅ PAGINATION (PAGE + SIZE)
    // =================================================
    @GetMapping("/pagination")
    public Page<Users> getUsersPagination(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return usersService.getUsersPagination(page, size);
    }
}
