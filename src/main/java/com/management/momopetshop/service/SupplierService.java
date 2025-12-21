package com.management.momopetshop.service;

import com.management.momopetshop.SupplierNotFoundException;
import com.management.momopetshop.model.Supplier;
import com.management.momopetshop.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    // READ ALL
    public List<Supplier> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public Supplier getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier tidak ditemukan"));
    }

    // CREATE
    public Supplier save(Supplier supplier) {
        return repository.save(supplier);
    }

    // UPDATE
    public Supplier update(Integer id, Supplier supplier) {
        Supplier existing = getById(id);
        existing.setNamaSupplier(supplier.getNamaSupplier());
        existing.setNoTelp(supplier.getNoTelp());
        return repository.save(existing);
    }

    // DELETE
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
