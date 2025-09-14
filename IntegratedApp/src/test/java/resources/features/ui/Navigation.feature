Feature: Navigation to different pages from the homepage
  Background:
    Given as a user I launch the website
    Then validate the header of the website

  @NavigationFromNavigationDropdown  @Regression
  Scenario Outline: Navigation from the Navigation dropdown in homepage
    When I mousehover on the Navigation dropdown"<dropdown>"
    Then I should see all the options in the dropdown
    When I mousehover on any option "<option>"
    Then I should see all the sub options for that option
    When I click on any option from the dropdown "<subOption>"
    Then I should be navigated to the respective page


    Examples:
    |dropdown|option   |subOption|
    |Electronics|Gaming|Gaming Keyboards|

  @NavigationFromNavigationBar  @Regression
  Scenario Outline: Navigation from the Navigation bar where dropdown option is not present in homepage
    When I click on any option from the Navigation bar "<navOption>"
    Then I should be navigated to the respective page from navigation "<navOption>"
    Then I validate any particular brand's header is present or not "<brandname>"

    Examples:
    |navOption      |brandname|
    |Mobiles & Tablets  | Motorola    |

  @NavigationFromNavigationBarProducts  @Regression
  Scenario Outline: Navigation from the Navigation bar where dropdown option is not present in homepage
    When I click on any option from the Navigation bar "<navOption>"
    Then I should be navigated to the respective page from navigation "<navOption>"
    Then I validate any particular brand's products are present or not "<brandname>"

    Examples:
      |navOption          |brandname|
      |Mobiles & Tablets  | Samsung |

  @InvalidScenarioWrongBrandHeaderSearch  @Regression   @NegativeScenarios
  Scenario Outline: Navigation from the Navigation bar where dropdown option is not present and search for wrong brand
    When I click on any option from the Navigation bar "<navOption>"
    Then I should be navigated to the respective page from navigation "<navOption>"
    Then I validate any particular brand's header is not present "<brandname>"

    Examples:
      |navOption      |brandname|
      |Mobiles & Tablets  |Fossil|
