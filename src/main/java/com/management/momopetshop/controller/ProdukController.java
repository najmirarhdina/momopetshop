package com.management.momopetshop.controller;

import com.management.momopetshop.model.Produk;
import com.management.momopetshop.service.ProdukService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produk")
public class ProdukController {

    private final ProdukService service;

    public ProdukController(ProdukService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produk> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Produk getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public Produk create(@RequestBody Produk produk) {
        return service.save(produk);
    }

    @PutMapping("/{id}")
    public Produk update(@PathVariable Integer id, @RequestBody Produk produk) {
        return service.update(id, produk);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "Produk berhasil dihapus";
    }
}
