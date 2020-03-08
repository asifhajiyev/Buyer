package com.gdg.buyer.service.impl;

import com.gdg.buyer.dto.ProductDto;
import com.gdg.buyer.entity.Order;
import com.gdg.buyer.entity.Product;
import com.gdg.buyer.exception.ProductNotFoundException;
import com.gdg.buyer.mapper.ProductMapper;
import com.gdg.buyer.repository.OrderRepository;
import com.gdg.buyer.repository.ProductRepository;
import com.gdg.buyer.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity addProduct(Product product) {
        productRepository.save(product);
        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }

    @Override
    public Product findByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public ResponseEntity acceptProduct(Product product) {
        Product p = findByName(product.getName());
        if (p != null) {
            p.setAmount(p.getAmount() + product.getAmount());
        } else {
            p = product;
        }

        System.out.println("before get order in acceptProduct buyer");
        Order order = orderRepository.findByOrderedProductNameAndReceiveIsFalse(p.getName());
        System.out.println("after get order in acceptProduct buyer");
        order.setReceive(true);
        orderRepository.save(order);

        addProduct(p);
        return new ResponseEntity("Product accepted", HttpStatus.OK);
    }

    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> products = new ArrayList<>();
        for (Product p : productRepository.findAll()) {
            products.add(ProductMapper.entityToDto(p));
        }
        return products;
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = null;
        try {
            product = productRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ProductNotFoundException("Product doesn't exist"));
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        return ProductMapper.entityToDto(product);
    }

    @Override
    public String deleteProductById(String id) {
        String response;
        ProductDto productDto = getProductById(id);
        if (productDto == null) {
            response = "Product doesn't exist";
        } else {
            productRepository.deleteById(Long.valueOf(id));
            response = "Deleted";
        }
        return response;
    }
}
