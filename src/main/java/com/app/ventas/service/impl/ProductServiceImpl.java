package com.app.ventas.service.impl;

import com.app.ventas.dto.ProductDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.maper.ProductMapper;
import com.app.ventas.persistence.entity.Product;
import com.app.ventas.persistence.repository.ProductRepository;
import com.app.ventas.service.ProductService;
import com.app.ventas.util.MessageConfirmDelete;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public List<ProductDto> findAll()  throws IllegalStateException {

        return productMapper.toProductDtoList(productRepository.findAll());
    }

    @Override
    public ProductDto findById(String id) throws ApiErrorException{
        try {
            Long idConver = Long.parseLong(id); // Convertir la cadena a Long
            Optional<Product> product = productRepository.findById(idConver);

            if (!product.isPresent()) {
                throw new ApiErrorException("Product local is not valid");
            }

            return productMapper.toProductDto(product.get());
        } catch (NumberFormatException e) {
            throw new ApiErrorException("Invalid parameter in URL: "+id+" is not number");
        }
    }

    @Override
    public ProductDto findByName(String name) throws ApiErrorException{
        Optional<Product> product = productRepository.findByName(name);
        if(!product.isPresent()){
            throw new ApiErrorException("Prdouct local is not valid");
        }
        return productMapper.toProductDto(product.get());
    }

    @Override
    public ProductDto save(Product product) throws UncheckedIOException {
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto update(Long id, Product product) throws ApiErrorException {
        if(product.getId() != id){
            throw new ApiErrorException("El id del body es diferente al de la url");
        }
        Optional<Product> productExist = productRepository.findById(id);
        if(!productExist.isPresent()){
            throw new ApiErrorException("Product local not exist");
        }
        return productMapper.toProductDto(productRepository.save(product));
    }

/*    public MessageConfirmDelete delete(Long id){
       String response;

        if(id > 0 && productRepository.existsById(id)){
            productRepository.deleteById(id);
            response = "Product deleted successfully";
        }else{
            response = "Product not found";
        }

        MessageConfirmDelete message = new MessageConfirmDelete(
                response,
                HttpStatus.OK.toString(),
                HttpMethod.DELETE.name(),
                HttpRequest.class.toString()
        );

        return message;
    }
 */
}
