@Compare

  Feature: Functionality of comparing different products

    Background:
      Given as a user I launch the website
      Then validate the header of the website

        @AddToCompare   @Regression @Smoke
      Scenario Outline:Verify able to add 2 products for comparison
        And I enter a product name "<product1>" in the searchbox and hit enter
        Then it should show the all the available products related to that product
        And I click on the first product from the list
        Then the product should be opened in a new tab
        And I check the compare checkbox for the product
        And I enter a product name "<product2>" in the searchbox and hit enter
        Then it should show the all the available products related to that product
        And I click on the first product from the list
        Then the product should be opened in a new tab
        And I check the compare checkbox for the product
        When I click on the compare pop up
        Then I should be able to navigate to the compare page
        Then the products should be present"<product1>""<product2>"
          Then I retrieve all the specifications values into excel

        Examples:
        |product1|product2|
        |realme 15T 5G|OPPO Reno14 Pro 5G   |

    @AddToCompareForSameModel   @Regression
    Scenario Outline:Verify able to add 2 products for comparison of same model
      And I enter a product name "<product>" in the searchbox and hit enter
      Then it should show the all the available products related to that product
      And I check the compare checkboxes of the first 2 products
      When I click on the compare pop up
      Then I should be able to navigate to the compare page
      Then I validate both model has same price

      Examples:
      |product|
      |Samsung Galaxy S24 5G Snapdragon       |

    @AddProductInAddToComparePg   @Regression
    Scenario Outline:Verify able to add 2 products for comparison of same model
      And I enter a product name "<product>" in the searchbox and hit enter
      Then it should show the all the available products related to that product
      And I check the compare checkboxes of the first 2 products
      When I click on the compare pop up
      Then I should be able to navigate to the compare page
      When I add a new product to compare from the compare page "<brandname>""<modelname>"
      Then I should be able to add the product

      Examples:
        |product|brandname|modelname|
        |Samsung Galaxy S24 5G Snapdragon|Apple|iPhone 16 (White, 128 GB)|

    @CrossLinkVerification  @Regression
    Scenario Outline:Verify X link in compare page
      And I enter a product name "<product>" in the searchbox and hit enter
      Then it should show the all the available products related to that product
      And I check the compare checkboxes of the first 2 products
      When I click on the compare pop up
      Then I should be able to navigate to the compare page
      When I click on the X link on one product
      Then there should be only on product"<product>"

      Examples:
        |product|
        |Samsung Galaxy S24 5G Snapdragon       |

    @InvalidComparison    @Regression   @NegativeScenarios
    Scenario Outline: Only same type of products can be compared
      And I enter a product name "<product1>" in the searchbox and hit enter
      Then it should show the all the available products related to that product
      And I click on the first product from the list
#      Then the product should be opened in a new tab ctrlA+del
      And I check the compare checkbox for the product
      And I enter a product name "<product2>" in the searchbox and hit enter
      Then it should show the all the available products related to that product
      And I click on the first product from the list
      When I try to check the compare checkbox of product
      Then it should show error message that it can't be added

      Examples:
        |product1|product2|
        |iPhone 16|Samsung Galaxy Fit3|
