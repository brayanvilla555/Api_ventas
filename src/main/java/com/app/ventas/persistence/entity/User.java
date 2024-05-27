package com.app.ventas.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del usuario es necesario")
    private String firstname;

    @NotBlank(message = "El apellido del usuario es necesario")
    private String lastname;

    @Column(unique=true)
    @NotBlank(message = "El username es obligatorio y unico")
    private String username;

    @NotBlank(message = "Si no pones una contraseña se genrara: nombre_año")
    private String password;

    //nos lo obliga SpringSecurity
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;



    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //creamos una lista con todos los permisos que pertenece a este usuario
        //tambien se debe incluir el rol como un permiso
        //puede havcer un error en getGrantedPermission============

        if(role == null) return new ArrayList<>();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));

        if(role.getAuthority() == null) return authorities;

        //el error es que no estamos trayendo los permisos almenos para comparar con el token
        role.getGrantedPermission().stream()
                .forEach(each ->{
                    String permissionName = each.getPermission().getName();
                    authorities.add(new SimpleGrantedAuthority(permissionName));
                });

        return authorities;

    }

    @PrePersist
    private void toCreate(){
        accountExpired = false;
        accountLocked = false;
        credentialsExpired = false;
        enabled = true;

    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
