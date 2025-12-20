package com.management.momopetshop;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Integer id) {
        super("Supplier dengan id " + id + " tidak ditemukan");
    }
}