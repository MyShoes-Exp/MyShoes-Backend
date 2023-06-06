package com.acme.myshoes.platform.orders.api.rest;

import com.acme.myshoes.platform.orders.mapping.OrderMapper;
import org.springframework.context.annotation.Bean;

public class OrdersController {

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper();
    }

}
