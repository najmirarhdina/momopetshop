package com.management.momopetshop.service;

import com.management.momopetshop.dto.*;
import com.management.momopetshop.model.*;
import com.management.momopetshop.repository.TransaksiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransaksiService {

    private final TransaksiRepository transaksiRepository;

    public TransaksiService(TransaksiRepository transaksiRepository) {
        this.transaksiRepository = transaksiRepository;
    }

    // =============================
    // POST /transaksi
    // =============================
    @Transactional
    public TransaksiResponse simpanTransaksi(TransaksiRequest request) {

        Transaksi transaksi = new Transaksi();
        transaksi.setIdUser(request.getIdUser());
        transaksi.setTanggal(LocalDateTime.now());
        transaksi.setTotal(BigDecimal.ZERO);

        List<DetailTransaksi> details = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (TransaksiItemDTO item : request.getItems()) {

            BigDecimal subtotal = item.getHarga()
                    .multiply(BigDecimal.valueOf(item.getQty()));

            DetailTransaksi detail = new DetailTransaksi();
            detail.setTransaksi(transaksi);
            detail.setIdProduk(item.getIdProduk());
            detail.setQty(item.getQty());
            detail.setHarga(item.getHarga());
            detail.setSubtotal(subtotal);

            details.add(detail);
            total = total.add(subtotal);
        }

        transaksi.setTotal(total);
        transaksi.setDetailTransaksi(details);

        Transaksi saved = transaksiRepository.save(transaksi);
        return mapToResponse(saved);
    }

    // =============================
    // GET /transaksi
    // =============================
    @Transactional(readOnly = true) // ⭐⭐⭐ WAJIB
    public List<TransaksiResponse> getAll() {

        List<Transaksi> transaksiList = transaksiRepository.findAll();
        List<TransaksiResponse> responses = new ArrayList<>();

        for (Transaksi t : transaksiList) {
            responses.add(mapToResponse(t));
        }

        return responses;
    }

    // =============================
    // GET /transaksi/{id}
    // =============================
    @Transactional(readOnly = true) // ⭐⭐⭐ WAJIB
    public TransaksiResponse getById(Integer id) {

        Transaksi transaksi = transaksiRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transaksi dengan ID " + id + " tidak ditemukan"));

        return mapToResponse(transaksi);
    }

    // =============================
    // MAPPING ENTITY → RESPONSE
    // =============================
    private TransaksiResponse mapToResponse(Transaksi transaksi) {

        TransaksiResponse res = new TransaksiResponse();
        res.setIdTransaksi(transaksi.getIdTransaksi());
        res.setIdUser(transaksi.getIdUser());
        res.setTanggal(transaksi.getTanggal());
        res.setTotal(transaksi.getTotal());

        List<DetailTransaksiResponse> items = new ArrayList<>();
        for (DetailTransaksi d : transaksi.getDetailTransaksi()) {

            DetailTransaksiResponse dr = new DetailTransaksiResponse();
            dr.setIdProduk(d.getIdProduk());
            dr.setQty(d.getQty());
            dr.setHarga(d.getHarga());
            dr.setSubtotal(d.getSubtotal());

            items.add(dr);
        }

        res.setItems(items);
        return res;
    }
}
