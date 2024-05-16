package com.app.ventas.service.impl;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.dto.SaleDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.maper.ProductMapper;
import com.app.ventas.maper.SaleMapper;
import com.app.ventas.persistence.entity.DetailSale;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.persistence.entity.Sale;
import com.app.ventas.persistence.repository.DetailSaleRepository;
import com.app.ventas.persistence.repository.ProductRepository;
import com.app.ventas.persistence.repository.SaleRepository;
import com.app.ventas.service.ProductService;
import com.app.ventas.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private DetailSaleRepository detailSaleRepository;

    private final SaleMapper saleMapper = new SaleMapper();

    @Override
    public List<SaleDto> findAll() {
        return saleMapper.toSaleDtoList(saleRepository.findAll());
    }

    @Override
    public SaleDto findById(String id) throws ApiErrorException {

        if (id == null) {
            throw new ApiErrorException("El ID no puede ser nulo");
        }

        try {
            Long parsedId = Long.parseLong(id);

            Optional<Sale> saleOptional = saleRepository.findById(parsedId);
            if (!saleOptional.isPresent()) {
                throw new ApiErrorException("No se encontró ninguna venta con el ID proporcionado: " + parsedId);
            }

            return saleMapper.toSaleDto(saleOptional.get());
        } catch (NumberFormatException e) {
            throw new ApiErrorException("El ID debe ser un número válido");
        }
    }

    @Override
    public SaleDto save(Sale sale) throws ApiErrorException {
        if(sale == null) {
            throw new ApiErrorException("El Sale no puede ser vacia");
        }

        if(sale.getDetailSales() == null || sale.getDetailSales().isEmpty()) {
            throw new ApiErrorException("Es necesario incluir los productos");
        }


        double totalPrice = 0;
        for (DetailSale detailSale : sale.getDetailSales()) {
            double priceTotalProdut = detailSale.getPrice() * detailSale.getAmount();
            totalPrice += priceTotalProdut;
        }

        sale.setPrice(totalPrice);
        Sale saleSave = saleRepository.save(sale);

        for (DetailSale detailSale : sale.getDetailSales()) {
            detailSale.setSale(saleSave);
            detailSaleRepository.save(detailSale);
        }

        return saleMapper.toSaleDto(saleSave);
    }

    @Override
    public SaleDto update(String id, Product product) throws ApiErrorException {
        return null;
    }

}
