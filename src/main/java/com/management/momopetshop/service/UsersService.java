package com.management.momopetshop.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.management.momopetshop.DataAlreadyExistsException;
import com.management.momopetshop.DataNotFoundException;
import com.management.momopetshop.model.Users;
import com.management.momopetshop.repository.UsersRepository;

@Service // Menandai class ini sebagai Service (business logic layer)
public class UsersService {

    // Logger untuk mencatat aktivitas aplikasi (info, warning, error)
    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    // Repository untuk akses data users ke database
    private final UsersRepository usersRepository;

    // Constructor Injection (best practice Spring)
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // =============================
    // GET ALL USERS
    // Mengambil seluruh data user dari database
    // =============================
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // =============================
    // GET USER BERDASARKAN ID
    // Jika ID tidak ditemukan, lempar DataNotFoundException
    // =============================
    public Users getUserById(Integer id) {
        return usersRepository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "User dengan ID " + id + " tidak ditemukan"
                        )
                );
    }

    // =============================
    // GET USER BERDASARKAN USERNAME
    // Digunakan untuk pencarian user berdasarkan username
    // =============================
    public Users getUserByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Username '" + username + "' tidak ditemukan"
                        )
                );
    }

    // =============================
    // SIMPAN USER (UMUM)
    // Digunakan untuk save/update data user
    // =============================
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    // =============================
    // CREATE USER BARU
    // Mengecek apakah username sudah ada sebelum disimpan
    // =============================
    public Users createUser(Users user) {

        // Validasi: username tidak boleh duplikat
        if (usersRepository.existsByUsername(user.getUsername())) {
            throw new DataAlreadyExistsException("Username sudah digunakan");
        }

        // Simpan user baru ke database
        return usersRepository.save(user);
    }

    // =============================
    // DELETE USER BERDASARKAN ID
    // Jika user tidak ditemukan, lempar DataNotFoundException
    // =============================
    public void deleteUserById(Integer id) {

        // Log ketika request delete dipanggil
        log.info("Request delete user dengan ID: {}", id);

        // Cari user berdasarkan ID
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> {
                    // Log jika user tidak ditemukan
                    log.warn("GAGAL delete: User dengan ID {} tidak ditemukan", id);
                    return new DataNotFoundException(
                            "User dengan ID " + id + " tidak ditemukan"
                    );
                });

        // Hapus user dari database
        usersRepository.delete(user);

        // Log jika delete berhasil
        log.info(
                "BERHASIL delete user dengan ID: {}, username: {}",
                id,
                user.getUsername()
        );
    }
}
