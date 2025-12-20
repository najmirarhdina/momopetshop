package com.management.momopetshop;

public class DataAlreadyExistsException extends RuntimeException {

    public DataAlreadyExistsException(String message) {
        super(message);
    }
}