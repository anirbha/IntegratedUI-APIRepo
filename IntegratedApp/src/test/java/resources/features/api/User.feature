@UserAPI
Feature: Validate different operations using on users with User endpoint

  @CreateUserObject @api  @Regression
  Scenario Outline: Created  a new User Object
  Given I create a new user object
  Then the status should be "<statusCode>"
    Examples:
    |statusCode|
    |200       |

  @CreateUserArray @api  @Regression
  Scenario Outline: Created new Users Object
    Given I create a list of users with array
    Then the status should be "<statusCode>"
    Examples:
      |statusCode|
      |200       |

  @CreateUserList   @api  @Regression
  Scenario Outline: Created new Users Object
    Given I create a list of new users object
    Then the status should be "<statusCode>"
    Examples:
      |statusCode|
      |200       |

  @UserLogin  @api  @Regression
  Scenario Outline:User Logs into the system
    Given the user logs in "<username>" "<password>"
    Then the status should be "<statusCode>"
    Then user login message should be displayed "<LoginMsg>"

    Examples:
    |username|password|statusCode|LoginMsg|
    |JOSI    |password123|200    |logged in|

  @UserLogOff  @api  @Regression
  Scenario Outline: User logs off from the system
    Given the user logged out from the current session
    Then the status should be "<statusCode>"

    Examples:
    |statusCode|
    |200       |
  @UpdateUserDetails @api  @Regression
  Scenario Outline: Updating a user details
    Given I update a user details with username "<username>"
    Then the status should be "<statusCode>"

    Examples:
    |username|statusCode|
    | JOSI   |200       |

  @DeleteUserDetails @api  @Regression
  Scenario Outline: Deleting a user which are already logged in
    Given I delete a user with username "<username>""<password>" which is already logged in
    Then the status should be "<statusCode>"
    Then the username should be present in the response "<username>"

    Examples:
      |username|password|statusCode|
      | ashRa   | bowling    |200   |

  @DeleteInvalid @api  @Regression
  Scenario Outline: Deleting a user which are not logged in
    Given I delete a user with username "<username>" which is already not logged in
    Then the status should be "<statusCode>"
    Examples:
      |username|statusCode|
      | JAB    |404   |

  @UpdateNotLoggedInUserDetails @api  @Regression  @NegativeScenarios
  Scenario Outline: Trying to update a user details which are not logged in
    Given I update a user details with username "<username>""<password>" which are not logged in
    Then the status should be "<statusCode>"

    Examples:
      |username|password|statusCode|
      | cr7   | football|404       |
  @UserDetailGet @api @Regression
  Scenario Outline: Fetch the user details
    Given I fetch a user details "<user>"
    Then the status should be "<responsecode>"
    Then I validate the JSON Schema of it
    Examples:
    |user|responsecode|
    |user1|200|