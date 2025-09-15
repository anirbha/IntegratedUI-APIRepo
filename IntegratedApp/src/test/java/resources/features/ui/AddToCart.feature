@AddToCart
Feature: Validate different functionality regarding add to cart page

  Background:
    Given as a user I launch the website
    Then validate the header of the website

  @IncreaseQuantityOfAddedItem  @Regression @Smoke
  Scenario Outline: Add product and increase the number and navigate to Payment
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart
    And I increase the number of the product added "<number>"
    Then the number of products will be updated accordingly
    When I click on the Place Order button
    Then I should be navigated to the Checkout page

  Examples:
    |product|number|
    |Samsung Galaxy Fit3|3  |

  @IncreaseQuantityUptoLimit  @Regression
  Scenario Outline: Add product and increase the number till it is allowed
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    When I try to add "<number>" of quantity
    Then it should show alert message

    Examples:
      |product|number|
      |Titan Edge|7  |

  @RemoveFromCartFunc  @Regression @Smoke
  Scenario Outline: Validate remove functionality
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart
    When I click on the Remove link
    Then the remove pop up should be appeared
    When I click on the "<button>" on the remove pop up
    Then I validate the action based on the pressed button "<button>" "<product>"

    Examples:
      |product|button|
      |Samsung Galaxy Fit3|Remove|
      |Samsung Galaxy Fit3|Cancel|

  @PriceCalculation  @Regression
  Scenario Outline: Validate price details calculation
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart
    And I calculate the total discount on the item
    Then it should match with the displayed discount

    Examples:
    |product|
    |Samsung Galaxy Fit3|

   @SaveLaterOrMoveCartLinkFunc   @Regression
  Scenario Outline: Save for later/Move to cart functionality
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart
    When I click on the "Save for later" link
     Then the product should be added to Save for later table along with the confirmation message
     When I click on the "Move to cart" link
     Then the product should be added to that cart along with the confirmation message

    Examples:
      |product|
      |Samsung Galaxy Fit3|

  @DecreaseTheCountBelowOne   @Regression   @NegativeScenarios
  Scenario Outline: Can not reduce the the product number less than one
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart
    Then it should not allow to reduce the quantity

    Examples:
    |product|
    |Samsung Galaxy Fit3|

  @ValidateIconsInSavedFrLater    @Regression   @NegativeScenarios
  Scenario Outline: Product count increase/decrease link is disabled in saved for later segment
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart
    When I click on the "Save for later" link
    Then the product should be added to Save for later table along with the confirmation message
    Then the plus and minus icon should be disabled

    Examples:
      |product|
      |Samsung Galaxy Fit3|

  @NavigationToCartIcon   @Regression
  Scenario Outline: Navigation from Cart icon
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Add to Cart button if the product is available
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart
    When I click on the Place Order button
    Then I should be navigated to the Checkout page
    When I click on the Home link
    Then I should be navigated to the home page
    When I click on the cart icon
    Then I should be navigated to the Shopping Cart page
    Then I validate the correct product is added to the cart

    Examples:
      |product|
      |Samsung Galaxy Fit3|

  @NoAddToCartButton    @Regression  @NegativeScenarios
  Scenario Outline: No Add To Cart button for product currently unavailable
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I validate the product is Out of stock
    Then add to cart button should not be present

    Examples:
      |product|
      |iPhone 17 Pro|

  @BuyNowBtnFunc  @Regression @Smoke
  Scenario Outline: Search and buy a product
    And I enter a product name "<product>" in the searchbox and hit enter
    Then it should show the all the available products related to that product
    And I click on the first product from the list
    Then the product should be opened in a new tab
    When I click on the Buy Now button
    Then I should be navigated to the Checkout page

    Examples:
      |product|
      |Samsung Galaxy Fit3|