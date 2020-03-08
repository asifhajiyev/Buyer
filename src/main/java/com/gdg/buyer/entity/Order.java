package com.gdg.buyer.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderedProductName;
    private long orderedProductAmount;

    @Column(name="isReceived", columnDefinition = "tinyint default false")
    private boolean receive;
}
