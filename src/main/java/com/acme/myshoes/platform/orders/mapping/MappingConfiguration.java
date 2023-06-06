package com.acme.myshoes.platform.orders.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("OrderMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper();
    }

}
