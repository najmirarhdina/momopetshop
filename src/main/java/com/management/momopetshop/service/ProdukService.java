package com.management.momopetshop.service;

import com.management.momopetshop.dto.ProdukRequest;
import com.management.momopetshop.model.Kategori;
import com.management.momopetshop.model.Produk;
import com.management.momopetshop.model.Supplier;
import com.management.momopetshop.repository.KategoriRepository;
import com.management.momopetshop.repository.ProdukRepository;
import com.management.momopetshop.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdukService {

    private final ProdukRepository produkRepository;
    private final KategoriRepository kategoriRepository;
    private final SupplierRepository supplierRepository;

    public ProdukService(
            ProdukRepository produkRepository,
            KategoriRepository kategoriRepository,
            SupplierRepository supplierRepository
    ) {
        this.produkRepository = produkRepository;
        this.kategoriRepository = kategoriRepository;
        this.supplierRepository = supplierRepository;
    }

    // ================= GET =================
    public List<Produk> findAll() {
        return produkRepository.findAll();
    }

    public Produk findById(Integer id) {
        return produkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));
    }

    // ================= CREATE (DTO) =================
    public Produk save(ProdukRequest req) {

        Kategori kategori = kategoriRepository.findById(req.getIdKategori())
                .orElseThrow(() -> new RuntimeException("Kategori tidak ditemukan"));

        Supplier supplier = supplierRepository.findById(req.getIdSupplier())
                .orElseThrow(() -> new RuntimeException("Supplier tidak ditemukan"));

        Produk produk = new Produk();
        produk.setNamaProduk(req.getNamaProduk());
        produk.setHarga(req.getHarga());
        produk.setStok(req.getStok());
        produk.setKategori(kategori);
        produk.setSupplier(supplier);

        return produkRepository.save(produk);
    }

    // ================= UPDATE =================
    public Produk update(Integer id, ProdukRequest req) {

        Produk produk = findById(id);

        Kategori kategori = kategoriRepository.findById(req.getIdKategori())
                .orElseThrow(() -> new RuntimeException("Kategori tidak ditemukan"));

        Supplier supplier = supplierRepository.findById(req.getIdSupplier())
                .orElseThrow(() -> new RuntimeException("Supplier tidak ditemukan"));

        produk.setNamaProduk(req.getNamaProduk());
        produk.setHarga(req.getHarga());
        produk.setStok(req.getStok());
        produk.setKategori(kategori);
        produk.setSupplier(supplier);

        return produkRepository.save(produk);
    }

    // ================= DELETE =================
    public void delete(Integer id) {
        produkRepository.deleteById(id);
    }
}
