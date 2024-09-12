package com.pankajproduct.order_service.respository;

import com.pankajproduct.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends
        JpaRepository<Order, Long> {
}
