@DynamicSelection
Feature: Validate different ways to select element dynamically

  Background:
    Given as a user I launch the website
    Then validate the header of the website


  @SelectItemByStorage  @Regression  @ui
  Scenario Outline: Search by a product name and change its capacity
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    Then I validate the storage "<storage>" of the product with my requirement

    Examples:
      | product | storage |
      |iPhone 16 Pro Max|512 GB|

  @SelectItemByColour  @Regression  @ui
  Scenario Outline: Search by a product name and change its colour
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    Then I validate the colour "<colour>"of the product with my requirement

    Examples:
    | product | colour |
    |iPhone 16 Pro Max|White Titanium|




