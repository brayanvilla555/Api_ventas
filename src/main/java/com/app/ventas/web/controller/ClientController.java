package com.app.ventas.web.controller;

import com.app.ventas.dto.ClientDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.Client;
import com.app.ventas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/client")
@PreAuthorize("hasAuthority('CLIENT')")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> findAll() throws ApiErrorException {
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ClientDto>> findByName(@PathVariable String name) throws ApiErrorException {
            return ResponseEntity.ok().body(clientService.findAllName(name));
    }

    @PostMapping("/")
    private ResponseEntity<ClientDto> save(@RequestBody Client client) throws ApiErrorException{
        return ResponseEntity.ok().body(clientService.save(client));
    }

    @PutMapping("/{id}")
    private ResponseEntity<ClientDto> update(@PathVariable Long id,@RequestBody Client client) throws ApiErrorException{
        return ResponseEntity.ok().body(clientService.update(id, client));
    }

    @DeleteMapping("/{id}")
    private Map<String, String> delete(@PathVariable Long id) throws ApiErrorException{
        return  clientService.delete(id);
    }
}
