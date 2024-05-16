package com.app.ventas.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del producto es necesario")
    @Size(min = 4, max = 50)
    private String name;

    @Column(nullable = true)
    @Length(min = 0, max = 255)
    private String description;

    private double price;

    private int stock;

    //@JsonBackReference
    //@JsonIgnoreProperties("product")
    //@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    //private List<DetailSale> detailSales;


}
