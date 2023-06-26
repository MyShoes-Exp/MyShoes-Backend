package com.acme.myshoes.platform.cucumber;

import com.acme.myshoes.platform.MyShoesBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = MyShoesBackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MyShoesBackendApplication.class,
        loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
