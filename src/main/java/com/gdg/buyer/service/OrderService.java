package com.gdg.buyer.service;

import com.gdg.buyer.dto.OrderDto;
import com.gdg.buyer.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    String makeOrder(OrderDto orderDto);

    Order findByOrderedProductNameAndReceiveIsFalse(String orderedProductName);

    List<OrderDto> getOrders();

    OrderDto findById(Long id);

    String deleteOrderById(String id);
}
