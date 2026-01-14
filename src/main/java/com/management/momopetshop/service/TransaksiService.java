package com.management.momopetshop.service;

import com.management.momopetshop.dto.*;
import com.management.momopetshop.InvalidRequestException;
import com.management.momopetshop.model.*;
import com.management.momopetshop.repository.ProdukRepository;
import com.management.momopetshop.repository.TransaksiRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransaksiService {

    private final TransaksiRepository transaksiRepository;
    private final ProdukRepository produkRepository;

    public TransaksiService(
            TransaksiRepository transaksiRepository,
            ProdukRepository produkRepository
    ) {
        this.transaksiRepository = transaksiRepository;
        this.produkRepository = produkRepository;
    }

    // =============================
    // CREATE TRANSAKSI
    // =============================
    @Transactional
    public TransaksiResponse simpanTransaksi(TransaksiRequest request) {

        Transaksi transaksi = new Transaksi();
        transaksi.setIdUser(request.getIdUser());
        transaksi.setTanggal(LocalDateTime.now());
        transaksi.setTotal(BigDecimal.ZERO);

        List<DetailTransaksi> detailList = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (TransaksiItemDTO item : request.getItems()) {

            Produk produk = produkRepository.findById(item.getIdProduk())
                    .orElseThrow(() ->
                            new RuntimeException("Produk tidak ditemukan")
                    );

            if (item.getHarga() != null &&
                item.getHarga().compareTo(produk.getHarga()) != 0) {
                throw new InvalidRequestException("Harga tidak valid");
            }

            if (produk.getStok() < item.getQty()) {
                throw new InvalidRequestException("Stok produk tidak mencukupi");
            }

            produk.setStok(produk.getStok() - item.getQty());
            produkRepository.save(produk);

            BigDecimal subtotal = produk.getHarga()
                    .multiply(BigDecimal.valueOf(item.getQty()));

            DetailTransaksi detail = new DetailTransaksi();
            detail.setTransaksi(transaksi);
            detail.setIdProduk(item.getIdProduk());
            detail.setQty(item.getQty());
            detail.setHarga(produk.getHarga());
            detail.setSubtotal(subtotal);

            detailList.add(detail);
            total = total.add(subtotal);
        }

        transaksi.setTotal(total);
        transaksi.setDetailTransaksi(detailList);

        Transaksi saved = transaksiRepository.save(transaksi);
        return mapToResponse(saved);
    }

    // =============================
    // GET ALL
    // =============================
    @Transactional(readOnly = true)
    public List<TransaksiResponse> getAll() {

        List<TransaksiResponse> responses = new ArrayList<>();
        for (Transaksi t : transaksiRepository.findAll()) {
            responses.add(mapToResponse(t));
        }
        return responses;
    }

    // =============================
    // GET BY ID
    // =============================
    @Transactional(readOnly = true)
    public TransaksiResponse getById(Integer id) {

        Transaksi transaksi = transaksiRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transaksi tidak ditemukan")
                );

        return mapToResponse(transaksi);
    }

    // =================================================
    // ✅ PAGINATION (PAGE + SIZE SAJA)
    // =================================================
    @Transactional(readOnly = true)
    public Page<TransaksiResponse> getPagination(int page, int size) {

        if (page < 0) {
            throw new InvalidRequestException("Page tidak boleh kurang dari 0");
        }

        if (size <= 0) {
            throw new InvalidRequestException("Size harus lebih dari 0");
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("idTransaksi").ascending()
        );

        return transaksiRepository
                .findAll(pageable)
                .map(this::mapToResponse);
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
