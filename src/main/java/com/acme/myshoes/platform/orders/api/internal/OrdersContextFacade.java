package com.acme.myshoes.platform.orders.api.internal;
import com.acme.myshoes.platform.orders.domain.model.Order;

import java.util.List;
public interface OrdersContextFacade {
    List<Order> getAllOrders();
}
