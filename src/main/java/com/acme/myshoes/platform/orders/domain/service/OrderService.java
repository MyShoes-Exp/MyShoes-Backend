package com.acme.myshoes.platform.orders.domain.service;
import com.acme.myshoes.platform.orders.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    List<Order> getAll();
    Page<Order> getAll(Pageable pageable);
    Order create(Order order);

    Order getById(Long orderId);

    Order update(Long id, Order order);
    ResponseEntity<?> delete(Long orderId);


}
