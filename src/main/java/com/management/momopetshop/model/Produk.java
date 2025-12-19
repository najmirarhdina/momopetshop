package com.management.momopetshop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produk")
public class Produk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produk")
    private Integer idProduk;

    @Column(name = "nama_produk", nullable = false)
    private String namaProduk;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal harga;

    @Column(nullable = false)
    private int stok;

    @Column(name = "id_kategori")
    private Integer idKategori;

    @Column(name = "id_supplier")
    private Integer idSupplier;

    // getter & setter
    public Integer getIdProduk() { return idProduk; }
    public void setIdProduk(Integer idProduk) { this.idProduk = idProduk; }

    public String getNamaProduk() { return namaProduk; }
    public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }

    public BigDecimal getHarga() { return harga; }
    public void setHarga(BigDecimal harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public Integer getIdKategori() { return idKategori; }
    public void setIdKategori(Integer idKategori) { this.idKategori = idKategori; }

    public Integer getIdSupplier() { return idSupplier; }
    public void setIdSupplier(Integer idSupplier) { this.idSupplier = idSupplier; }
}
