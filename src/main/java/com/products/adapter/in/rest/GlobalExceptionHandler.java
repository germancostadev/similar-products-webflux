package com.products.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public Mono<ResponseEntity<Void>> handleProductNotFound(ProductNotFoundException e) {
        return Mono.just(ResponseEntity.notFound().build());
    }
}
