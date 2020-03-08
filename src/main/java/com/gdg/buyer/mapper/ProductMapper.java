package com.gdg.buyer.mapper;

import com.gdg.buyer.dto.ProductDto;
import com.gdg.buyer.entity.Product;

public class ProductMapper {
    public static Product dtoToEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setAmount(productDto.getAmount());
        return product;
    }

    public static ProductDto entityToDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setAmount(product.getAmount());
        return productDto;
    }
}
