package com.acme.myshoes.platform.orders.mapping;

import com.acme.myshoes.platform.orders.domain.persistence.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("OrderMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper();
    }
   @Bean
    public OrderItemMapper orderItemMapper(){return new OrderItemMapper();}
}
