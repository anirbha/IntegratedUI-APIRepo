@Sort
Feature: Validation of sorting features

  Background:
    Given as a user I launch the website
    Then validate the header of the website

  @AllSortingOptions  @Regression @Smoke  @ui
  Scenario Outline: Validate different sorting options
    And I enter a product name "<product>" in the searchbox and hit enter
    Then I validate different available sorting options
    Examples:
    |product|
    |headphone|

  @SortingFunc  @Regression  @ui
  Scenario Outline: Validate sorting functionality of the "<sorting>"
    And I enter a product name "<product>" in the searchbox and hit enter
    When I click on the "<sorting>" sorting to validate sorting
    Then the product should be sorted according to "<sorting>"

    Examples:
    |product|sorting|
    |earbuds|Popularity|
    |earbuds|Price -- Low to High|
    |earbuds|Price -- High to Low|
    |pendrive|Newest First        |
    |earbuds|Discount            |

  @SortingFuncWithReload  @Regression  @ui
  Scenario Outline: Validate sorting persisting after page reloads
    And I enter a product name "<product>" in the searchbox and hit enter
    When I click on the "<sorting>" sorting to validate sorting
    Then the product should be sorted according to "<sorting>"
    When I reload the page
    Then the product should be sorted according to "<sorting>"

    Examples:
      |product|sorting|
      |earbuds|Popularity|
      |earbuds|Price -- Low to High|
      |earbuds|Price -- High to Low|
      |pendrive|Newest First        |
      |earbuds|Discount            |


