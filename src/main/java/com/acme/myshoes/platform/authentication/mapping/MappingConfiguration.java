package com.acme.myshoes.platform.authentication.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("authenticationMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public RoleMapper roleMapper() { return new RoleMapper(); }
}
