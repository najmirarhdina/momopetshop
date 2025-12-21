package com.management.momopetshop.repository;

import com.management.momopetshop.model.Transaksi;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {

    @Query("""
        SELECT DISTINCT t
        FROM Transaksi t
        LEFT JOIN FETCH t.detailTransaksi
    """)
    List<Transaksi> findAllWithDetail();

    @Query("""
        SELECT t
        FROM Transaksi t
        LEFT JOIN FETCH t.detailTransaksi
        WHERE t.idTransaksi = :id
    """)
    Optional<Transaksi> findByIdWithDetail(@Param("id") Integer id);
}
