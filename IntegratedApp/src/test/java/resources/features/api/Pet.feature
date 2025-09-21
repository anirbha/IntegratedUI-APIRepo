@PetAPI

Feature: Validate different API responses for Pet endpoint

  @PetPost @api  @Regression
  Scenario Outline: Create a new pet identity
    Given I create a new pet
    Then the status code should be "<responsecode>"
    Then the newly created id should have the same name and status
    Examples:
      | responsecode |
      | 200      |

  @PetGet @api @Regression
  Scenario Outline: Get a new pet identity
    Given I fetch a pet by id "<id>"
    Then the status code should be "<responsecode>"
    Then the id should have the same name and status
    Examples:
      |id|responsecode|
      |1|200     |

  @PetPut @api  @Regression
  Scenario Outline: Update pet identity
    Given I update the pet name and status
    Then the status code should be "<responsecode>"
    Then the name and status should be same with the expected one
    Examples:
     |responsecode|
     |200     |

  @PetDelete    @api  @Regression
  Scenario Outline: Delete pet identity
    Given I delete the pet id "<id>"
    Then the status code should be "<responsecode>"

    Examples:
      |id|responsecode|
      |18|200     |

  @FindByStatus  @api  @Regression
  Scenario Outline: Finds pets by status
    Given I fetch pets by status "<status>"
    Then the status code should be "<responsecode>"

    Examples:
    |status   |responsecode|
    |available|200         |
    |pending  |200         |
    |sold     |200         |


@FetchPetByInvalidId @api  @Regression
Scenario Outline:  Get pet by invalid/non-existent ID
    Given I fetch a pet by an invalid id "<id>"
    Then the status code should be "<responsecode>"
    Then the response should the error message "<message>"
  Examples:
  |id|responsecode|message|
  |456| 404       |Pet not found|

@InvalidPayLoadForPut  @api  @Regression
Scenario Outline:  Update pet with invalid status
  Given I try to update with invalid status
  Then the status code should be "<responsecode>"
  Then the response should the error message "<message>"
  Examples:
    |responsecode|message|
    |400     |bad input  |

  @DeleteUsingInvalidId  @api  @Regression
Scenario Outline:  Delete pet by invalid ID
  Given I try to delete the pet id "<id>"
  Then the status code should be "<responsecode>"

  Examples:
    |id|responsecode|
    |9999999999|404 |

  @SearchByInvalidStatus @api  @Regression
  Scenario Outline:  Find pets by invalid status value
    Given I fetch pets by status "<status>"
    Then the status code should be "<responsecode>"
    Then the response should be blank

    Examples:
      |status   |responsecode|
      |bowling  |200         |




