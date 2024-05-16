package com.app.ventas.maper;

import com.app.ventas.dto.DetailSaleDto;
import com.app.ventas.dto.SaleDto;
import com.app.ventas.persistence.entity.DetailSale;
import com.app.ventas.persistence.entity.Sale;
import jakarta.persistence.MappedSuperclass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedSuperclass
public class DetailSaleMapper {


    public static DetailSaleDto toDetailSaleDto(DetailSale detailSale) {
        DetailSaleDto detailSaleDto = new DetailSaleDto();
         return detailSaleDto.builder().sale(detailSale.getSale())
                 .product(detailSale.getProduct())
                 .amount(detailSale.getAmount())
                 .price(detailSale.getPrice())
                 .build();
    }

    public static DetailSale toDetailSale(DetailSaleDto detailSaleDto) {
        DetailSale detailSale = new DetailSale();
        return detailSale.builder().sale(detailSaleDto.getSale())
                .product(detailSaleDto.getProduct())
                .amount(detailSaleDto.getAmount())
                .price(detailSaleDto.getPrice())
                .build();
    }


    public Set<DetailSaleDto> toSaleDetailDtoList(Set<DetailSale> detailSales) {
        Set<DetailSaleDto> salesDto = new HashSet<>();
        detailSales.stream().forEach(sale ->{
            salesDto.add( this.toDetailSaleDto(sale));
        });
        return salesDto;
    }

    public Set<DetailSale> toSaleDetailList(Set<DetailSaleDto> detailSalesDto) {
        Set<DetailSale> datailSales = new HashSet<>();
        detailSalesDto.stream().forEach(sale ->{
            datailSales.add( this.toDetailSale(sale));
        });
        return datailSales;
    }

}
