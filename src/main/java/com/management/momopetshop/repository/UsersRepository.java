package com.management.momopetshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.momopetshop.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    // cari user berdasarkan username
    Optional<Users> findByUsername(String username);

    // cek apakah username sudah ada (buat validasi)
    boolean existsByUsername(String username);
}

