package com.management.momopetshop.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.momopetshop.DataAlreadyExistsException;
import com.management.momopetshop.DataNotFoundException;
import com.management.momopetshop.model.Supplier;
import com.management.momopetshop.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    // =============================
    // GET ALL
    // =============================
    public List<Supplier> getAll() {
        return repository.findAll();
    }

    // =============================
    // GET BY ID
    // =============================
    public Supplier getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Data supplier dengan ID " + id + " tidak ditemukan"
                        )
                );
    }

    // =============================
    // CREATE SUPPLIER
    // =============================
    public Supplier save(Supplier supplier) {
        if (repository.existsByNamaSupplierIgnoreCase(supplier.getNamaSupplier())) {
            throw new DataAlreadyExistsException(
                    "Supplier dengan nama '" + supplier.getNamaSupplier() +
                    "' sudah terdaftar dalam sistem"
            );
        }
        return repository.save(supplier);
    }

    // =============================
    // UPDATE SUPPLIER BY ID
    // =============================
    public Supplier update(Integer id, Supplier supplier) {

        Supplier existing = repository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Supplier dengan ID " + id + " tidak ditemukan"
                        )
                );

        existing.setNamaSupplier(supplier.getNamaSupplier());
        existing.setNoTelp(supplier.getNoTelp());

        return repository.save(existing);
    }

    // =============================
    // DELETE
    // =============================
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException(
                    "Data supplier dengan ID " + id + " tidak ditemukan"
            );
        }
        repository.deleteById(id);
    }

    // =================================================
    // ✅ PAGINATION (PAGE + SIZE SAJA)
    // =================================================
    public Page<Supplier> getPagination(int page, int size) {

        if (page < 0) {
            throw new IllegalArgumentException("Page tidak boleh kurang dari 0");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Size harus lebih dari 0");
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("idSupplier").ascending()
        );

        return repository.findAll(pageable);
    }
}
