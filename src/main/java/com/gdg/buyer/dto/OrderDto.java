package com.gdg.buyer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderedProductName;
    private long orderedProductAmount;
    private boolean received;
    private String customerName;
}
