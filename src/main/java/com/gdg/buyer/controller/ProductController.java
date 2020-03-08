package com.gdg.buyer.controller;

import com.gdg.buyer.entity.Order;
import com.gdg.buyer.entity.Product;
import com.gdg.buyer.service.ProductService;
import com.gdg.buyer.service.impl.ProductServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("accept-product")
    public ResponseEntity acceptProduct(@RequestBody Product product) {
        return productService.acceptProduct(product);
    }

    @ApiOperation(value = "Gets all products")
    @GetMapping
    public ResponseEntity getProducts() {
        return new ResponseEntity(productService.getProducts(), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets product by given id")
    @GetMapping("{id}")
    public ResponseEntity getProduct(@PathVariable String id) {
        return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes product by given id")
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProductById(@PathVariable String id) {
        return new ResponseEntity(productService.deleteProductById(id), HttpStatus.OK);
    }

}
