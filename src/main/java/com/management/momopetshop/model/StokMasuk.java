package com.management.momopetshop.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stok_masuk")
public class StokMasuk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stok")
    private Integer idStok;

    @Column(name = "id_produk", nullable = false)
    private Integer idProduk;

    @Column(name = "id_supplier", nullable = false)
    private Integer idSupplier;

    @Column(nullable = false)
    private Integer jumlah;

    @Column(nullable = false)
    private LocalDateTime tanggal;

    // Getter & Setter
    public Integer getIdStok() { return idStok; }
    public void setIdStok(Integer idStok) { this.idStok = idStok; }

    public Integer getIdProduk() { return idProduk; }
    public void setIdProduk(Integer idProduk) { this.idProduk = idProduk; }

    public Integer getIdSupplier() { return idSupplier; }
    public void setIdSupplier(Integer idSupplier) { this.idSupplier = idSupplier; }

    public Integer getJumlah() { return jumlah; }
    public void setJumlah(Integer jumlah) { this.jumlah = jumlah; }

    public LocalDateTime getTanggal() { return tanggal; }
    public void setTanggal(LocalDateTime tanggal) { this.tanggal = tanggal; }
}
