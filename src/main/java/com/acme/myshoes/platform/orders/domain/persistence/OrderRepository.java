package com.acme.myshoes.platform.orders.domain.persistence;

import com.acme.myshoes.platform.orders.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
