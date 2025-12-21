package com.management.momopetshop.controller;

import com.management.momopetshop.dto.TransaksiRequest;
import com.management.momopetshop.dto.TransaksiResponse;
import com.management.momopetshop.service.TransaksiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

    private final TransaksiService transaksiService;

    public TransaksiController(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    // ================= CREATE =================
    @PostMapping
    public TransaksiResponse simpan(@RequestBody TransaksiRequest request) {
        return transaksiService.simpanTransaksi(request);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<TransaksiResponse> getAll() {
        return transaksiService.getAll();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public TransaksiResponse getById(@PathVariable Integer id) {
        return transaksiService.getById(id);
    }
}
