package com.app.ventas.dto;

import com.app.ventas.persistence.entity.Product;
import com.app.ventas.persistence.entity.Sale;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailSaleDto {

    private Sale sale;

    private Product product;

    private int amount;

    private Double price;

}
