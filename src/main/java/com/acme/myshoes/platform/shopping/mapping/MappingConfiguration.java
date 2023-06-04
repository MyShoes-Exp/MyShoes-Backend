package com.acme.myshoes.platform.shopping.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("shoppingMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ShoppingCartMapper shoppingCartMapper() {
        return new ShoppingCartMapper();
    }
}
