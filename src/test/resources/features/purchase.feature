Feature: Complete Purchase Flow

  Scenario: Complete a purchase with the two most expensive products
    Given I open the Saucedemo website
    When I login with valid username "standard_user" and password "secret_sauce"
    Then I should be navigated to the products page
    When I add the two most expensive products to the cart
    And I click on the cart button
    Then I should be on the cart page
    And I should see the selected products listed
    When I click the checkout button
    Then I should be on the checkout page
    When I fill in the checkout information with first name "John", last name "Doe", and postal code "12345"
    And I click the continue button
    Then I should be on the overview page
    And I should verify the items total before taxes
    And the URL should be "https://www.saucedemo.com/checkout-step-two.html"
    When I click the finish button
    Then I should see the "Thank You" and "dispatched" messages
