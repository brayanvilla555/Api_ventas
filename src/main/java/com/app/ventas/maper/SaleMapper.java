package com.app.ventas.maper;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.dto.SaleDto;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.persistence.entity.Sale;
import jakarta.persistence.MappedSuperclass;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class SaleMapper {

    private static DetailSaleMapper detailSaleMapper = new DetailSaleMapper();

    public static SaleDto toSaleDto(Sale sale) {
        SaleDto saleDto = new SaleDto();
        return saleDto.builder().id(sale.getId())
                .client(sale.getClient())
                .sale_date(sale.getSale_date())
                .price(sale.getPrice())
                .state(sale.getState())
                .detailSales(sale.getDetailSales())
                .build();

    }

    public static Sale toSale(SaleDto saleDto) {
        Sale sale = new Sale();
        return sale.builder().id(saleDto.getId())
                .client(saleDto.getClient())
                .sale_date(saleDto.getSale_date())
                .price(saleDto.getPrice())
                .state(saleDto.getState())
                .detailSales(saleDto.getDetailSales())
                .build();

    }

    public List<SaleDto> toSaleDtoList(List<Sale> sales) {
        List<SaleDto> salesDto = new ArrayList<>();
        sales.stream().forEach(product ->{
            salesDto.add( this.toSaleDto(product));
        });
        return salesDto;
    }
}
