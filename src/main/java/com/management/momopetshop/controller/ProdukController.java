package com.management.momopetshop.controller;

import com.management.momopetshop.dto.ProdukRequest;
import com.management.momopetshop.model.Produk;
import com.management.momopetshop.service.ProdukService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produk")
public class ProdukController {

    private final ProdukService produkService;

    public ProdukController(ProdukService produkService) {
        this.produkService = produkService;
    }

    @GetMapping
    public List<Produk> getAll() {
        return produkService.findAll();
    }

    @GetMapping("/{id}")
    public Produk getById(@PathVariable Integer id) {
        return produkService.findById(id);
    }

    @PostMapping
    public Produk create(@RequestBody ProdukRequest req) {
        return produkService.save(req);
    }

    @PutMapping("/{id}")
    public Produk update(
            @PathVariable Integer id,
            @RequestBody ProdukRequest req)
    {
        return produkService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        produkService.delete(id);
    }
}
