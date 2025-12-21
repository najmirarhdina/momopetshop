package com.management.momopetshop.service;

import com.management.momopetshop.model.Kategori;
import com.management.momopetshop.repository.KategoriRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriService {

    private final KategoriRepository repository;

    public KategoriService(KategoriRepository repository) {
        this.repository = repository;
    }

    public Kategori save(Kategori kategori) {
        return repository.save(kategori);
    }

    public List<Kategori> findAll() {
        return repository.findAll();
    }

    public Kategori findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori tidak ditemukan"));
    }

    public Kategori update(Integer id, Kategori kategori) {
        Kategori k = findById(id);
        k.setNamaKategori(kategori.getNamaKategori());
        return repository.save(k);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
