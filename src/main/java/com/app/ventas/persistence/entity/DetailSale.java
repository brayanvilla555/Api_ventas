package com.app.ventas.persistence.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "sale_id")
    //@JsonIgnoreProperties("detailSales")
    @JsonIgnore
    private Sale sale;

    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("detailSales")
    private Product product;

    private int amount;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double price;

}
