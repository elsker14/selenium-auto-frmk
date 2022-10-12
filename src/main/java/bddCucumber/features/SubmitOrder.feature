Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommerge Page

  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product  <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage.
    Examples:
      | name                       | password             | productName     |
      | iancujianu98@gmail.com     | Pernutepufoase14*    | ZARA COAT 3     |
      | iancujianu.works@gmail.com | Marcelcuceritorul14* | ADIDAS ORIGINAL |