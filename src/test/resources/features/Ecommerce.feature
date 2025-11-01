Feature: E-commerce Workflow Automation

  Scenario: Verify user can login with valid credentials
    Given user launches the browser
    And navigates to SauceDemo login page
    When user enters valid username and password
    And clicks on login button
    Then user should be redirected to Products page

  Scenario: Verify user can add a product to cart
    Given user is logged in to SauceDemo
    When user adds a product to the cart
    Then product should appear in the cart

  Scenario: Verify user can checkout successfully
    Given user has a product in the cart
    When user proceeds to checkout and enters details
    Then order should be placed successfully

  Scenario: Verify user can logout successfully
    Given user is on the Products page
    When user clicks logout
    Then user should be redirected to login page
