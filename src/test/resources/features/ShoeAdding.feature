Feature: Shoe Adding
  As a Developer
  I want to add a Shoe through API
  So that I can be available to the users
  Background:
    Given The Endpoint "http://localhost:%d/api/v1/shoes" is available

    @shoe-adding
    Scenario: Add Shoe with unique name
      When A Post Request is sent with values "Nike Dunk SB", 40, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fexclusiveshopperu.com%2Ftienda%2Fhombres%2Fnike-sb-dunk-low-los-angeles-dodgers%2F&psig=AOvVaw3YV6M5tYRbAMXJc-WH4_wK&ust=1687838345143000&source=images&cd=vfe&ved=0CA4QjRxqFwoTCOiRtvqF4P8CFQAAAAAdAAAAABAD", 500, "1", "1"
      Then The Response Status should be 201
      And A Shoe Resource is included in the Response Body, with values "Nike Dunk SB", 40, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fexclusiveshopperu.com%2Ftienda%2Fhombres%2Fnike-sb-dunk-low-los-angeles-dodgers%2F&psig=AOvVaw3YV6M5tYRbAMXJc-WH4_wK&ust=1687838345143000&source=images&cd=vfe&ved=0CA4QjRxqFwoTCOiRtvqF4P8CFQAAAAAdAAAAABAD", 500, "1", "1"

      @shoe-duplicate
      Scenario: Add Shoe with existing name
        Given  A Shoe Resource is included in the Response Body, with values "Nike Dunk SB", 40, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fexclusiveshopperu.com%2Ftienda%2Fhombres%2Fnike-sb-dunk-low-los-angeles-dodgers%2F&psig=AOvVaw3YV6M5tYRbAMXJc-WH4_wK&ust=1687838345143000&source=images&cd=vfe&ved=0CA4QjRxqFwoTCOiRtvqF4P8CFQAAAAAdAAAAABAD", 500, "1", "1" is already stored
        When A Post Request is sent with values "Nike Dunk SB", 40, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fexclusiveshopperu.com%2Ftienda%2Fhombres%2Fnike-sb-dunk-low-los-angeles-dodgers%2F&psig=AOvVaw3YV6M5tYRbAMXJc-WH4_wK&ust=1687838345143000&source=images&cd=vfe&ved=0CA4QjRxqFwoTCOiRtvqF4P8CFQAAAAAdAAAAABAD", 500, "1", "1"
        Then The Response Status should be 400
        And A Message is included in the Response Body, with value "Shoe with name 'Nike Dunk SB' already exists"