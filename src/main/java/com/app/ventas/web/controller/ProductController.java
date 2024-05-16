package com.app.ventas.web.controller;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UncheckedIOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@PreAuthorize("hasAuthority('PRODUCT')")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> findAll()  throws ApiErrorException {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable String id) throws ApiErrorException{
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDto> findByname(@Valid @PathVariable String name) throws ApiErrorException {

        return ResponseEntity.ok().body(productService.findByName(name));
    }

    @PostMapping("/")
    public ResponseEntity<ProductDto> save(@Valid @RequestBody Product product) throws ApiErrorException{

        return ResponseEntity.ok().body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@Valid @PathVariable Long id,@RequestBody Product product) throws ApiErrorException{
        return ResponseEntity.ok().body(productService.update(id, product));
    }


}
