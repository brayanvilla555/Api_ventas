package com.app.ventas.dto;

import com.app.ventas.persistence.entity.DetailSale;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private double price;

    private int stock;

    //private Set<DetailSale> detailSales;
}
