package com.management.momopetshop.repository;

import com.management.momopetshop.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepository extends JpaRepository<Produk, Integer> {
}
