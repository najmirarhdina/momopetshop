package com.management.momopetshop.service;

import com.management.momopetshop.model.StokMasuk;
import com.management.momopetshop.repository.StokMasukRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StokMasukService {

    private final StokMasukRepository repository;

    public StokMasukService(StokMasukRepository repository) {
        this.repository = repository;
    }

    public StokMasuk save(StokMasuk stokMasuk) {
        return repository.save(stokMasuk);
    }

    public List<StokMasuk> findAll() {
        return repository.findAll();
    }

    public StokMasuk findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data stok masuk tidak ditemukan"));
    }

    public StokMasuk update(Integer id, StokMasuk stokMasuk) {
        StokMasuk s = findById(id);
        s.setIdProduk(stokMasuk.getIdProduk());
        s.setIdSupplier(stokMasuk.getIdSupplier());
        s.setJumlah(stokMasuk.getJumlah());
        s.setTanggal(stokMasuk.getTanggal());
        return repository.save(s);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
