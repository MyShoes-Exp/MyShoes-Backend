package com.acme.myshoes.platform.orders.domain.service;

import com.acme.myshoes.platform.orders.domain.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface OrderItemService {
    List<OrderItem> getAll();
    Page<OrderItem> getAll(Pageable pageable);
    OrderItem create(OrderItem orderItem);

    OrderItem getById(Long orderItemId);

    OrderItem update(Long id, OrderItem orderItem);
    ResponseEntity<?> delete(Long orderItemId);
}
