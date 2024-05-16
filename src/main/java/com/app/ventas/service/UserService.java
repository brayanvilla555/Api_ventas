package com.app.ventas.service;


import com.app.ventas.dto.UserDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.persistence.entity.User;

import java.io.UncheckedIOException;
import java.util.List;

public interface UserService {
    List<UserDto> findAll() throws ApiErrorException;
    UserDto findById(Long id) throws ApiErrorException;
    UserDto save(User user) throws ApiErrorException;
    UserDto update(Long id ,User user) throws ApiErrorException;
}
