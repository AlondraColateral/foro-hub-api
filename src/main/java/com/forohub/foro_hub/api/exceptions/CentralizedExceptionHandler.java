package com.forohub.foro_hub.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDto>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleBadCredentials(BadCredentialsException ex) {
        // Mensaje genérico para no revelar si el usuario existe o no
        ErrorDto error = new ErrorDto("Autenticación", "Credenciales inválidas.");
        return ResponseEntity.status(401).body(error); // 401 Unauthorized
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFound(IllegalArgumentException ex) {
        ErrorDto error = new ErrorDto("Recurso no encontrado", ex.getMessage());
        return ResponseEntity.status(404).body(error); // 404 Not Found
    }
}