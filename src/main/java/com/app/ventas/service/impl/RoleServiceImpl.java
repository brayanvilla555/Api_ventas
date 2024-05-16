package com.app.ventas.service.impl;

import com.app.ventas.dto.SaleDto;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.persistence.entity.Role;
import com.app.ventas.persistence.repository.RoleRepository;
import com.app.ventas.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> findAll() throws UncheckedIOException{
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) throws UncheckedIOException {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if(roleOptional.isPresent()){
            return roleOptional.get();
        }else{
            return null;
        }
    }

}
