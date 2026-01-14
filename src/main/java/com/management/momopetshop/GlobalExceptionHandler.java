package com.management.momopetshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // USER SUDAH ADA (400)
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserExists(UserAlreadyExistsException ex) {
        return new ErrorResponse(
                "USER_ALREADY_EXISTS",
                ex.getMessage()
        );
    }

    // USER TIDAK DITEMUKAN (404)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException ex) {
        return new ErrorResponse(
                "USER_NOT_FOUND",
                ex.getMessage()
        );
    }

        // DATA NOT FOUND (404)
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDataNotFound(DataNotFoundException ex) {
    return new ErrorResponse(
            "DATA_NOT_FOUND",
            ex.getMessage()
    );
    }


    // DATA ALREADY EXISTS (404)
    @ExceptionHandler(DataAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDataAlreadyExists(DataAlreadyExistsException ex) {
    return new ErrorResponse(
            "DATA_ALREADY_EXISTS",
            ex.getMessage()
    );
   }

    //  REQUEST BODY TIDAK VALID (400)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidBody(HttpMessageNotReadableException ex) {
        return new ErrorResponse(
                "INVALID_REQUEST_BODY",
                "Request body tidak valid atau bukan JSON"
        );
    }

    //  ERROR LAIN (500)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneric(Exception ex) {
        return new ErrorResponse(
                "INTERNAL_ERROR",
                "Terjadi kesalahan pada server"
        );
    }

    // =============================
    // FORMAT RESPONSE ERROR (SATU!)
    // =============================
    public static class ErrorResponse {
        private final String code;
        private final String message;
        private final LocalDateTime timestamp = LocalDateTime.now();

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() { return code; }
        public String getMessage() { return message; }
        public LocalDateTime getTimestamp() { return timestamp; }
    }
}
