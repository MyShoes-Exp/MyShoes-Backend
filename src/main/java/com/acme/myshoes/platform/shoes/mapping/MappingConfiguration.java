package com.acme.myshoes.platform.shoes.mapping;

import com.acme.myshoes.platform.shoes.domain.model.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("shoesMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public CollectionMapper collectionMapper() {
        return new CollectionMapper();
    }
    @Bean
    public ShoeMapper shoeMapper() {
        return new ShoeMapper();
    }
}
