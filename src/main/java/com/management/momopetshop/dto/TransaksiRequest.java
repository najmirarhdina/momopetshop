package com.management.momopetshop.dto;

import java.util.List;

public class TransaksiRequest {

    private Integer idUser;
    private String tanggal;
    private List<TransaksiItemDTO> items;

    // ===== Getter & Setter =====

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public List<TransaksiItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TransaksiItemDTO> items) {
        this.items = items;
    }
}
