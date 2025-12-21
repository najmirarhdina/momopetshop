package com.management.momopetshop.controller;

import com.management.momopetshop.model.StokMasuk;
import com.management.momopetshop.service.StokMasukService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stok-masuk")
public class StokMasukController {

    private final StokMasukService service;

    public StokMasukController(StokMasukService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public StokMasuk create(@RequestBody StokMasuk stokMasuk) {
        return service.save(stokMasuk);
    }

    // READ ALL
    @GetMapping
    public List<StokMasuk> getAll() {
        return service.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public StokMasuk getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public StokMasuk update(
            @PathVariable Integer id,
            @RequestBody StokMasuk stokMasuk) {
        return service.update(id, stokMasuk);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
