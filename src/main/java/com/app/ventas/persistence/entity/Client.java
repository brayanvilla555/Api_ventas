package com.app.ventas.persistence.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 4, max = 50)
    @NotBlank(message = "El nombre es necesario")
    private String name;

    @Column(nullable = false)
    @Size(min = 4, max = 50)
    @NotBlank(message = "El apellido es necesario")
    private String lastname;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;

    @Size(min = 8, max = 10)
    private String identificationNumber;

    @Enumerated(EnumType.STRING)
    private StateClient stateClient;


    @Column
    @JsonFormat(pattern = "dd-MM-yyyy, HH:mm:ss")
    private LocalDateTime created_at;

    //@JsonManagedReference
    //@JsonIgnoreProperties("client")
    //@JsonIgnore descomentar desde aqui en caso lo necesiten usar
    //@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private List<Sale> sales;


    @PrePersist
    private void onCreated(){
        this.created_at = LocalDateTime.now();
    }

    //Creamos el Enum donde identificamos los pocibles tipos de identificación
    public enum IdentificationType{
        DNI, CEDULA
    }
    //Creamos el Enum dar valores establecidos
    public enum StateClient{
        ACTIVO, INACTIVO
    }
}
