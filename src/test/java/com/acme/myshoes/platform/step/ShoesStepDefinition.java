package com.acme.myshoes.platform.step;

import com.acme.myshoes.platform.shoes.resource.CreateShoeResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.lang.Long;

public class ShoesStepDefinition {
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;
    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }

    @When("A Post Request is sent with values {string}, {int}, {string}, {int}, {Long}, {Long}")
    public void aPostRequestIsSentWithValues(String name, int size, String img, int price, Long collection_id, Long category_id) {
        CreateShoeResource resource = new CreateShoeResource()
                .withName(name)
                .withSize(size)
                .withImg(img)
                .withPrice(price)
                .withCollection(collection_id)
                .withCategory(category_id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateShoeResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }
}
