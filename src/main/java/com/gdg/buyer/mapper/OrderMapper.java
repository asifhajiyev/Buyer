package com.gdg.buyer.mapper;

import com.gdg.buyer.dto.OrderDto;
import com.gdg.buyer.entity.Order;

public class OrderMapper {
    public static Order dtoToEntity(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }
        Order order = new Order();
        order.setOrderedProductName(orderDto.getOrderedProductName());
        order.setOrderedProductAmount(orderDto.getOrderedProductAmount());
        order.setReceive(orderDto.isReceived());
        return order;
    }

    public static OrderDto entityToDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderedProductName(order.getOrderedProductName());
        orderDto.setOrderedProductAmount(order.getOrderedProductAmount());
        orderDto.setReceived(order.isReceive());
        return orderDto;
    }

}
