package com.management.momopetshop;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =============================
    // REQUEST BODY TIDAK VALID (400)
    // =============================
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestBody(HttpMessageNotReadableException ex) {
        return new ErrorResponse(
                "INVALID_REQUEST_BODY",
                "Request body tidak valid atau bukan JSON"
        );
    }

    // =============================
    // USER TIDAK DITEMUKAN (404)
    // =============================
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(DataNotFoundException ex) {
        return new ErrorResponse(
                "USER_NOT_FOUND",
                ex.getMessage()
        );
    }

    // =============================
    // USER SUDAH ADA (409)
    // =============================
    @ExceptionHandler(DataAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExists(DataAlreadyExistsException ex) {
        return new ErrorResponse(
                "USER_ALREADY_EXISTS",
                ex.getMessage()
        );
    }

    // =============================
    // ERROR LAIN (500)
    // =============================
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneric(Exception ex) {
        return new ErrorResponse(
                "INTERNAL_ERROR",
                "Terjadi kesalahan pada server"
        );
    }

    // =============================
    // FORMAT RESPONSE ERROR
    // =============================
    public static class ErrorResponse {
        private final String code;
        private final String message;
        private final LocalDateTime timestamp;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
