package com.app.ventas.service;

import com.app.ventas.dto.SaleDto;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.persistence.entity.Role;
import com.app.ventas.persistence.entity.Sale;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> findAll() throws UncheckedIOException;
    Role findById(Long id) throws UncheckedIOException;
}
