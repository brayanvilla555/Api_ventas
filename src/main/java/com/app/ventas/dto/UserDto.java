package com.app.ventas.dto;


import com.app.ventas.persistence.entity.Role;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean credentialsExpired;

    private boolean enabled;

    private String role;

}
