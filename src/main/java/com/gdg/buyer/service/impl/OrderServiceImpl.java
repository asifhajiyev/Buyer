package com.gdg.buyer.service.impl;

import com.gdg.buyer.client.SupplierFeignClient;
import com.gdg.buyer.dto.OrderDto;
import com.gdg.buyer.dto.ProductDto;
import com.gdg.buyer.entity.Order;
import com.gdg.buyer.entity.Product;
import com.gdg.buyer.exception.OrderNotFoundException;
import com.gdg.buyer.mapper.OrderMapper;
import com.gdg.buyer.mapper.ProductMapper;
import com.gdg.buyer.model.Response;
import com.gdg.buyer.repository.OrderRepository;
import com.gdg.buyer.repository.ProductRepository;
import com.gdg.buyer.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private SupplierFeignClient supplierFeignClient;
    private OrderRepository orderRepository;
    private ProductServiceImpl productService;

    public OrderServiceImpl(SupplierFeignClient supplierFeignClient, OrderRepository orderRepository, ProductServiceImpl productService) {
        this.supplierFeignClient = supplierFeignClient;
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Override
    public String makeOrder(OrderDto orderDto) {

        String serviceResponse = supplierFeignClient.makeOrder(orderDto);

        ObjectMapper mapper = new ObjectMapper();
        Response response = new Response();

        try {
            response = mapper.readValue(serviceResponse, Response.class);
        } catch (Exception ex) {
            response.setMessage("JSON mapping exception");
            return response.getMessage();
        }

        if (response.getCode() == 1) {

            orderDto.setReceived(true);

            Product product = productService.findByName(orderDto.getOrderedProductName());
            if (product != null) {
                product.setAmount(product.getAmount() + orderDto.getOrderedProductAmount());
            } else {
                product = new Product();
                product.setName(orderDto.getOrderedProductName());
                product.setAmount(orderDto.getOrderedProductAmount());
            }

            productService.addProduct(product);

        } else if (response.getCode() == 2) { //order sent to seller but not enough in warehouse
            Order o = findByOrderedProductNameAndReceiveIsFalse(orderDto.getOrderedProductName());
            if (o != null) {
                o.setOrderedProductAmount(o.getOrderedProductAmount() + orderDto.getOrderedProductAmount());
                orderRepository.save(o);
                return response.getMessage();
            }

        }
        orderRepository.save(OrderMapper.dtoToEntity(orderDto));
        return response.getMessage();
    }

    @Override
    public Order findByOrderedProductNameAndReceiveIsFalse(String orderedProductName) {
        Order order = orderRepository.findByOrderedProductNameAndReceiveIsFalse(orderedProductName);
        return order;
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orders = new ArrayList<>();
        for (Order o : orderRepository.findAll()) {
            orders.add(OrderMapper.entityToDto(o));
        }
        return orders;
    }

    @Override
    public OrderDto findById(Long id) {
        Order order;
        try {
            order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order doesn't exist with this id"));
        } catch (OrderNotFoundException e) {
            return null;
        }
        return OrderMapper.entityToDto(order);
    }

    @Override
    public String deleteOrderById(String id) {
        String response;
        OrderDto orderDto = findById(Long.valueOf(id));
        if (orderDto == null) {
            response = "Order doesn't exist";
        } else {
            orderRepository.deleteById(Long.valueOf(id));
            response = "Deleted";
        }
        return response;
    }
}
