package com.app.ventas.maper;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.dto.UserDto;
import com.app.ventas.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        return userDto.builder().id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .accountExpired(user.isAccountExpired())
                .accountLocked(user.isAccountLocked())
                .credentialsExpired(user.isCredentialsExpired())
                .enabled(user.isEnabled())
                .role(user.getRole().getName().name())
                .build();
    }


    public User toUser(UserDto userDto) {
        User user = new User();
        return user.builder().id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .accountExpired(userDto.isAccountExpired())
                .accountLocked(userDto.isAccountLocked())
                .credentialsExpired(userDto.isCredentialsExpired())
                .enabled(userDto.isEnabled())
                .build();
    }

    public List<UserDto> toListUserDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        users.stream().forEach( user -> {
            userDtos.add(this.toUserDto(user));
        });

        return userDtos;
    }
}
