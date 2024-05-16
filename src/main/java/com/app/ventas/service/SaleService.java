package com.app.ventas.service;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.dto.SaleDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.persistence.entity.Sale;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

public interface SaleService {
    List<SaleDto> findAll() throws ApiErrorException;
    SaleDto findById(String id) throws ApiErrorException;
    SaleDto save(Sale sale) throws ApiErrorException;
    SaleDto update(String id, Product product) throws ApiErrorException;
}
