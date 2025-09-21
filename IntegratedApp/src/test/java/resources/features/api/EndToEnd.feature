@E2E
Feature: Covering end to end scenarios

  Background:
    Given the pet store has "available" pets

  @E2E_first    @api  @Regression
  Scenario Outline: User register,logs in, select pet from the available lists and purchase order successfully
    Given a new user registers
    And the user logs in with the same username
    When the user requests the list of "<status>" pets
    And the user selects a pet with id "<id>"
    And the user places an order for pet
    Then the order should be created successfully
    Then the user should be able to retrieve the order status using the order id "<id>"
    Then the pet with id "<id>" should have status "<currentStatus>"


    Examples:
    |status|id|currentStatus|
    |available|5|processing |



  @E2E_second   @api  @Regression
  Scenario Outline: User logs in, views pets and updates information
    Given the user logs in "<username>" "<password>"
    Then user login message should be displayed "<LoginMsg>"
    When the user updates the a pet
    Then the name and status should be same


    Examples:
    |username|password|LoginMsg|
    |annbhi |password |logged in|