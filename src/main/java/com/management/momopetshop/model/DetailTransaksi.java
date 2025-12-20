package com.management.momopetshop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detail_transaksi")
public class DetailTransaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Integer idDetail;

    @Column(name = "id_transaksi", nullable = false)
    private Integer idTransaksi;

    @Column(name = "id_produk", nullable = false)
    private Integer idProduk;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal harga;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    // Getter & Setter
    public Integer getIdDetail() { return idDetail; }
    public void setIdDetail(Integer idDetail) { this.idDetail = idDetail; }

    public Integer getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(Integer idTransaksi) { this.idTransaksi = idTransaksi; }

    public Integer getIdProduk() { return idProduk; }
    public void setIdProduk(Integer idProduk) { this.idProduk = idProduk; }

    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }

    public BigDecimal getHarga() { return harga; }
    public void setHarga(BigDecimal harga) { this.harga = harga; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
