package com.app.ventas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del permiso es necesario")
    private String name;

    // Relación con roles, no es necesaria si no se desea una relación bidireccional
    //JsonIgnore
    //@ManyToMany(mappedBy = "permissions")
    //private Set<Role> roles;

}
