Feature: Validate different API responses for Store endpoint

 @StoreInventory @api  @Regression
 Scenario Outline: To get pet inventories by status
   Given I fetch the inventories by status
   Then the status code should be "<responsecode>" for inventory
   Then the number of pets with different status should be shown
   Examples:
     |responsecode|
     |200     |

  @OrderPurchase @api  @Regression
  Scenario Outline: To place order to purchase
    Given I order for the purchasing the pet
    Then the status code should be "<responsecode>" for inventory
    Then response should match with payload

    Examples:
      |responsecode|
      |200     |

  @FindPurchaseByOrderId @api  @Regression
  Scenario Outline: Find Purchase by order id
    Given I fetch the purchase by order id "<id>"
    Then the status code should be "<responsecode>" for inventory
    Then id in the response should match with the id
    Examples:
      |id|responsecode|
      |8|200     |

  @DeletePurchaseByOrderId @api  @Regression
  Scenario Outline: Delete pet identity
    Given I delete the purchase by order id "<id>"
    Then the status code should be "<responsecode>" for inventory
    Then id should be present in the message

    Examples:
      |id|responsecode|
      |22|200     |

  @InvalidPostOrder  @api  @Regression
  Scenario Outline: To place order to purchase
    Given I order for the purchasing the pet with invalid Data
    Then the status code should be "<responsecode>" for inventory
    Then error message should match "<error msg>"

    Examples:
      |responsecode|error msg|
      |500     |something bad happened|

  @FindPurchaseWithInvalidOrder  @api  @Regression
  Scenario Outline: To find purchase with invalid order id
    Given I fetch the purchase by order id "<id>"
    Then the status code should be "<responsecode>" for inventory
    Then error message should match "<error msg>"

    Examples:
      |id|responsecode|error msg|
      |25  |404     |Order not found|