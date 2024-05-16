package com.app.ventas.service;

import com.app.ventas.dto.ClientDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.Client;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

public interface ClientService {
    List<ClientDto> findAll() throws ApiErrorException;

    List<ClientDto> findAllName(String name) throws ApiErrorException;

    ClientDto save(Client client) throws ApiErrorException;

    ClientDto update(Long id, Client client) throws ApiErrorException;

    Map<String, String> delete(Long id) throws ApiErrorException;
}
