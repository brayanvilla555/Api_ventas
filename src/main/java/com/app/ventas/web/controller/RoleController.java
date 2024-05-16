package com.app.ventas.web.controller;

import com.app.ventas.persistence.entity.Role;
import com.app.ventas.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
@PreAuthorize("hasAuthority('ROLE')")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<Role>> findAll()  throws UncheckedIOException {

        return ResponseEntity.ok().body(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) throws UncheckedIOException{

        return ResponseEntity.ok().body(roleService.findById(id));
    }


}
