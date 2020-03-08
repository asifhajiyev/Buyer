package com.gdg.buyer.client;

import com.gdg.buyer.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "supplier-client", url = "http://localhost:8080/supplier/api")
public interface SupplierFeignClient {

    @PostMapping("/orders/add-order")
    String makeOrder(@RequestBody OrderDto orderDto);
}
