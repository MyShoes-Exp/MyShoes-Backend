package com.acme.myshoes.platform.orders.domain.persistence;

import com.acme.myshoes.platform.orders.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {

}
