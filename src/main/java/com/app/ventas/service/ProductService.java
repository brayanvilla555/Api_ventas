package com.app.ventas.service;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.util.MessageConfirmDelete;

import java.io.UncheckedIOException;
import java.util.List;

public interface ProductService {
    List<ProductDto> findAll() throws ApiErrorException;
    ProductDto findById(String id) throws ApiErrorException;
    ProductDto findByName(String name) throws ApiErrorException;
    ProductDto save(Product product) throws ApiErrorException;
    ProductDto update(Long id, Product product) throws ApiErrorException;
}
