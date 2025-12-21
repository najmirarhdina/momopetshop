package com.management.momopetshop.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.management.momopetshop.UserAlreadyExistsException;
import com.management.momopetshop.UserNotFoundException;
import com.management.momopetshop.model.Users;
import com.management.momopetshop.repository.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // =============================
    // GET ALL USERS  ✅ (DITAMBAH)
    // =============================
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // =============================
    // CARI USER BERDASARKAN ID
    // =============================
    public Users getUserById(Integer id) {
        return usersRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User dengan ID " + id + " tidak ditemukan")
                );
    }

    // =============================
    // CARI USER BERDASARKAN USERNAME
    // =============================
    public Users getUserByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("Username '" + username + "' tidak ditemukan")
                );
    }

    // =============================
    // SIMPAN USER (UMUM)
    // =============================
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    // =============================
    // CREATE USER BARU
    // =============================
    public Users createUser(Users user) {
        if (usersRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username sudah digunakan");
        }
        return usersRepository.save(user);
    }
}