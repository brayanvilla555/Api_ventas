package com.app.ventas.service.impl;

import com.app.ventas.dto.UserDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.maper.UserMapper;
import com.app.ventas.persistence.entity.User;
import com.app.ventas.persistence.repository.UserRepository;
import com.app.ventas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper = new UserMapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() throws ApiErrorException {
        return userMapper.toListUserDto(userRepository.findAll());
    }

    @Override
    public UserDto findById(Long id) throws ApiErrorException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new ApiErrorException("Not found user");
        }
        return userMapper.toUserDto(user.get());

    }

    @Override
    public UserDto save(User user) throws ApiErrorException {
        if(user == null){
            throw new ApiErrorException("Envia al usuario en el cuerpo de la peticion");
        }

        Optional<User> userExist = userRepository.findByUsername(user.getUsername());

        if(userExist.isPresent()){
            throw new ApiErrorException("El correo ya existe en el sistema");
        }

        String password = passwordEncoder.encode(user.getPassword());

        user.setPassword(password);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Long id ,User user) throws ApiErrorException {
        if(user == null || id <= 0){
            throw new ApiErrorException("Envia al usuario en el cuerpo de la peticion");
        }

        Optional<User> userExist = userRepository.findByUsername(user.getUsername());

        if(userExist.isPresent()){
            if(userExist.get().getUsername().equals(user.getUsername()) && userExist.get().getId() == id){
                user.setPassword(userExist.get().getPassword());
                return userMapper.toUserDto(userRepository.save(user));
            }else{
                throw new ApiErrorException("El username pertenece a otor usuario");
            }
        }else{
            throw new ApiErrorException("El usuario no existe");
        }

    }
}
