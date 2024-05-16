package com.app.ventas.web.controller;

import com.app.ventas.dto.SaleDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.Sale;
import com.app.ventas.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@PreAuthorize("hasAuthority('SALE')")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/")
    public List<SaleDto> findAll() throws ApiErrorException {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> findById(@Valid @PathVariable String id) throws ApiErrorException {
        return ResponseEntity.ok().body(saleService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<SaleDto> save(@Valid @RequestBody Sale sale) throws ApiErrorException {
        return ResponseEntity.ok().body(saleService.save(sale));
    }
}
