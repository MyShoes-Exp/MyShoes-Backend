package com.acme.myshoes.platform.designing.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("designingMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public DesignMapper designMapper() {
        return new DesignMapper();
    }
}
