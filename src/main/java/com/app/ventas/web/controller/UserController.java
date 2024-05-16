package com.app.ventas.web.controller;

import com.app.ventas.dto.UserDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.User;
import com.app.ventas.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UncheckedIOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() throws ApiErrorException {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) throws ApiErrorException{
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> save(@Valid @RequestBody User user) throws ApiErrorException {
        return ResponseEntity.ok(userService.save(user));
    }


    //este metodo todavia no está terminado asi que hay que revisarlo más adelante
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@Valid @PathVariable Long id,@RequestBody User user) throws ApiErrorException {
        return ResponseEntity.ok(userService.update(id,user));
    }
}
