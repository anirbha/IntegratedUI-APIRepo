@Pagination
Feature: Pagination

  Background:
    Given as a user I launch the website
    Then validate the header of the website

  @NavToPageByNumber  @Regression @Smoke  @ui
  Scenario Outline: Navigate to different page by page number
  And I enter a product name "<product>" in the searchbox and hit enter
  Then I validate the current page number
  When I click on a particular "<pageno>"
  Then I should be navigated to that page

  Examples:
    |product|pageno|
    |T-shirt|2      |

  @NavToPageByButton  @Regression @Smoke  @ui
  Scenario Outline: Navigate to pages by button "<button>"
    And I enter a product name "<product>" in the searchbox and hit enter
    Then I validate the current page number
    When I click on a particular "<pageno>"
    Then I should be navigated to that page
    When I click on the "<button>" button
    Then I should be navigated to "<button>" page

    Examples:
      |product|pageno|button|
      |T-shirt|3      |Next  |
      |T-shirt|3      |Previous  |

  @PreviousBtnDisabled  @Regression   @NegativeScenarios  @ui
  Scenario Outline: Validate the Previous button is disabled for the first page
    And I enter a product name "<product>" in the searchbox and hit enter
    Then I validate the current page number
    Then I validate the "Previous" button is disabled

    Examples:
    |product|
    |T-shirt|

  @NextBtnDisabled  @Regression  @NegativeScenarios  @ui
  Scenario Outline: Validate the Next button is disabled for the last page
    And I enter a product name "<product>" in the searchbox and hit enter
    Then I validate the current page number
    When I click on a particular "<pageno>"
    Then I should be navigated to that page
    Then I validate the "Next" button is disabled

    Examples:
    |product|pageno|
    |iphone17 pro|3|