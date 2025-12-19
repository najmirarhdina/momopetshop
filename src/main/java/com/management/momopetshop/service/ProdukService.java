package com.management.momopetshop.service;

import com.management.momopetshop.repository.ProdukRepository;
import com.management.momopetshop.model.Produk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdukService {

    private final ProdukRepository repository;

    public ProdukService(ProdukRepository repository) {
        this.repository = repository;
    }

    public List<Produk> getAll() {
        return repository.findAll();
    }

    public Produk getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));
    }

    public Produk save(Produk produk) {
        return repository.save(produk);
    }

    public Produk update(Integer id, Produk produk) {
        Produk p = getById(id);
        p.setNamaProduk(produk.getNamaProduk());
        p.setHarga(produk.getHarga());
        p.setStok(produk.getStok());
        p.setIdKategori(produk.getIdKategori());
        p.setIdSupplier(produk.getIdSupplier());
        return repository.save(p);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
