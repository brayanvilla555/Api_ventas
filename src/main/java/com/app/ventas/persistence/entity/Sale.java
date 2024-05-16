package com.app.ventas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Es necesario tener incluido al cliente")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy, HH:mm:ss")
    private LocalDateTime sale_date;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double price;

    @Enumerated(EnumType.STRING)
    private State state;

    //@JsonIgnoreProperties("sale")
    @NotNull(message = "Es necesario tener incluido a los productos")
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)//orphanRemoval = true
    private List<DetailSale> detailSales;



    @PrePersist
    private void toCreatedSale(){
        this.sale_date = LocalDateTime.now();
    }

    public enum State{
        PENDIENTE, FINALIZADA, ACEPTADO
    }
}

