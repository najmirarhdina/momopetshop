package com.management.momopetshop.controller;

import com.management.momopetshop.model.Kategori;
import com.management.momopetshop.service.KategoriService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kategori")
public class KategoriController {

    private final KategoriService kategoriService;

    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    @PostMapping
    public Kategori create(@RequestBody Kategori kategori) {
        return kategoriService.save(kategori);
    }

    @GetMapping
    public List<Kategori> getAll() {
        return kategoriService.findAll();
    }

    @GetMapping("/{id}")
    public Kategori getById(@PathVariable Integer id) {
        return kategoriService.findById(id);
    }

    @PutMapping("/{id}")
    public Kategori update(@PathVariable Integer id, @RequestBody Kategori kategori) {
        return kategoriService.update(id, kategori);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kategoriService.delete(id);
    }
}
