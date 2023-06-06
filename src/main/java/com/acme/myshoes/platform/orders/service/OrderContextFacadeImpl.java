package com.acme.myshoes.platform.orders.service;

import com.acme.myshoes.platform.orders.api.internal.OrdersContextFacade;
import com.acme.myshoes.platform.orders.domain.model.Order;
import com.acme.myshoes.platform.orders.domain.service.OrderService;


import java.util.List;

public class OrderContextFacadeImpl implements OrdersContextFacade {
    private final OrderService orderService;
    public OrderContextFacadeImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }
}
