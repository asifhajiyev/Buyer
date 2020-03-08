package com.gdg.buyer.repository;

import com.gdg.buyer.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByOrderedProductNameAndReceiveIsFalse(String orderedProductName);
}
