Feature: Validation of different filter functionality

  Background:
    Given as a user I launch the website
    Then validate the header of the website

  @SingleFilter  @Regression
  Scenario Outline: Validation of only "<filterType>" filter functionality
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I apply the "<filterType>" filter
    When I check the "<filterName>" checkbox under the filter
    Then the products should be filtered based on the applied filter
    Examples:
        | filterType   | product    | filterName|
        | Brand        | T-Shirt    | ADIDAS |
        | Gender       | T-Shirt    | Couple|
        | fabric       | T-Shirt    | Cotton Blend     |
        | Color        | T-Shirt    | White             |

  @PriceFilter  @Regression
  Scenario Outline: Validation of Price filter functionality
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I apply the minimum range of the price filter"<minimum>"
    And I apply the maximum range of the price filter"<maximum>"
    Then the products should be filtered based on the price filter

    Examples:
        | product    |minimum|maximum|
        | T-Shirt    | 300   | 1000  |

  @MultipleFilter  @Regression
  Scenario Outline: Validation of multiple filters functionality
    And I enter a product name "<product>" in the searchbox and hit enter
    When I apply multiple filters:
      | filterType | filterName|
      | Gender       | Couple|
      | fabric       | Cotton Blend|
      | Color        | White|
    Then the products should be filtered based on the applied multiple filters
    When I click one of the products from the list based on our choice
    Then the product should be opened in a new tab
    Then I validate the brand of the product with my requirement

    Examples:
    |product|
    |T-Shirt|

  @ClearFilter  @Regression
  Scenario Outline: Validation of clear filters functionality
    And I enter a product name "<product>" in the searchbox and hit enter
    And I apply the "<filterType>" filter
    When I check the checkboxes under the filter
    |ADIDAS|
    |PUMA  |
    |WROGN  |
    Then the checkboxes values should be displayed
    When I click on X any one filter "<removeFilter>"
    Then that filter should be removed
    When I click on Clear All link
    Then all the filters should be removed

    Examples:
    |product|filterType|removeFilter|
    |T-shirt|Brand     |ADIDAS        |

  @Noproductsfound  @Regression   @NegativeScenarios
  Scenario Outline: Validation of no products found
    And I enter a product name "<product>" in the searchbox and hit enter
    And I check the "<filterName>" checkbox under the filter
    When I apply the maximum range of the price filter"<maximum>"
    Then no products should be found

    Examples:
    |product|filterName|maximum|
    |iPhone 16   |Apple     |10000  |