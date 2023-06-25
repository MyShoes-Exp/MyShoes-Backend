package com.acme.myshoes.platform.authentication.api.internal.configuration;

import com.acme.myshoes.platform.authentication.api.internal.AuthenticationContextFacade;
import com.acme.myshoes.platform.authentication.api.internal.AuthenticationContextFacadeImpl;
import com.acme.myshoes.platform.authentication.domain.persistence.UserRepository;
import com.acme.myshoes.platform.authentication.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AuthenticationContextFacade authenticationContextFacade(UserService userService) {
        return new AuthenticationContextFacadeImpl(userService);
    }
}