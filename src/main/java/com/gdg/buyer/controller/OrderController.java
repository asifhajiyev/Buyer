package com.gdg.buyer.controller;

import com.gdg.buyer.dto.OrderDto;
import com.gdg.buyer.entity.Order;
import com.gdg.buyer.service.impl.OrderServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/make-order")
    public String makeOrder(@RequestBody OrderDto orderDto) {
        return orderService.makeOrder(orderDto);
    }

    @ApiOperation(value = "Gets all orders")
    @GetMapping
    public ResponseEntity getOrders() {
        return new ResponseEntity(orderService.getOrders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets order by given id")
    @GetMapping("{id}")
    public ResponseEntity getOrder(@PathVariable Long id) {
        return new ResponseEntity(orderService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes order by given id")
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteOrderById(@PathVariable String id) {
        return new ResponseEntity(orderService.deleteOrderById(id), HttpStatus.OK);
    }
}
