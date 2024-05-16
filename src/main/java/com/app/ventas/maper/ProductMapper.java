package com.app.ventas.maper;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.persistence.entity.Product;
import jakarta.persistence.MappedSuperclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MappedSuperclass
public class ProductMapper {

    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        //productDto.setDetailSales(product.getDetailSales());
        return productDto;
    }

    public static Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        //product.setDetailSales(productDto.getDetailSales());
        return product;
    }

    public List<ProductDto> toProductDtoList(List<Product> products) {
        List<ProductDto> productsDto = new ArrayList<>();
        products.stream().forEach(product ->{
            productsDto.add( this.toProductDto(product));
        });
        return productsDto;
    }
}
