package com.acme.myshoes.platform.orders.domain.persistence;

import com.acme.myshoes.platform.orders.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
