package com.gdg.buyer.service;

import com.gdg.buyer.dto.ProductDto;
import com.gdg.buyer.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseEntity addProduct(Product product);

    Product findByName(String productName);

    ResponseEntity acceptProduct(Product product);

    List<ProductDto> getProducts();

    ProductDto getProductById(String id);

    String deleteProductById(String id);
}
