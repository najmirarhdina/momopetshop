package com.management.momopetshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransaksiResponse {

    private Integer idTransaksi;
    private Integer idUser;
    private LocalDateTime tanggal;
    private BigDecimal total;
    private List<DetailTransaksiResponse> items;

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetailTransaksiResponse> getItems() {
        return items;
    }

    public void setItems(List<DetailTransaksiResponse> items) {
        this.items = items;
    }
}
