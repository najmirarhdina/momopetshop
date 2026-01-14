package com.management.momopetshop.service;

import com.management.momopetshop.DataAlreadyExistsException;
import com.management.momopetshop.DataNotFoundException;
import com.management.momopetshop.model.Kategori;
import com.management.momopetshop.repository.KategoriRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriService {

    private final KategoriRepository repository;

    public KategoriService(KategoriRepository repository) {
        this.repository = repository;
    }

    // =============================
    // GET ALL KATEGORI
    // =============================
    public List<Kategori> findAll() {
        return repository.findAll();
    }

    // =============================
    // GET ALL KATEGORI (PAGINATION)
    // =============================
    public Page<Kategori> findPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    // =============================
    // GET BY ID
    // =============================
    public Kategori findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Kategori dengan ID " + id + " tidak ditemukan"
                        )
                );
    }

    // =============================
    // CREATE KATEGORI
    // =============================
    public Kategori save(Kategori kategori) {
        if (repository.existsByNamaKategoriIgnoreCase(kategori.getNamaKategori())) {
            throw new DataAlreadyExistsException(
                    "Kategori dengan nama '" + kategori.getNamaKategori() +
                    "' sudah terdaftar dalam sistem"
            );
        }
        return repository.save(kategori);
    }

    // =============================
    // UPDATE KATEGORI
    // =============================
    public Kategori update(Integer id, Kategori kategori) {
        Kategori existing = findById(id);

        existing.setNamaKategori(kategori.getNamaKategori());

        return repository.save(existing);
    }

    // =============================
    // DELETE KATEGORI
    // =============================
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException(
                    "Kategori dengan ID " + id + " tidak ditemukan"
            );
        }
        repository.deleteById(id);
    }
}
