package com.management.momopetshop.repository;

import com.management.momopetshop.model.Produk;
import com.management.momopetshop.model.Kategori;
import com.management.momopetshop.model.Supplier; // <--- pastikan ini ada
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Integer> {

    boolean existsByNamaProdukAndKategoriAndSupplier(String namaProduk, Kategori kategori, Supplier supplier);

    // Contoh di ProdukRepository
boolean existsByNamaProdukAndKategoriAndSupplierAndIdProdukNot(
        String namaProduk, Kategori kategori, Supplier supplier, Integer idProduk
);

}
