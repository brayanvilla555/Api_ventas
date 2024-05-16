package com.app.ventas.dto;

import com.app.ventas.persistence.entity.Client;
import com.app.ventas.persistence.entity.DetailSale;
import com.app.ventas.persistence.entity.Sale;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDto {

    private Long id;

    private Client client;

    private LocalDateTime sale_date;

    private Double price;

    private Sale.State state;

    private List<DetailSale> detailSales;
}