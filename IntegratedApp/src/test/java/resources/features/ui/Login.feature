@Login
Feature: Validation of the login page and features

  Background:
    Given as a user I launch the website
    Then validate the header of the website

    @RegisterAndLogin   @Regression @Smoke  @ui
    Scenario: User tries to register with the already registered number
      Then I validate the presence of Login CTA popup
      When I click on the Login button
      Then it should show the Login text along with the enter mobile number textbox
      When I click on the Create an account link
      Then it should show the Signup texts in the page
      And I enter the registered phone number in the phone number text box
      When I click on the Continue button
      Then it should show Login text again

    @RegistrationWithInvalidNumbers   @NegativeScenarios  @Regression  @ui
    Scenario Outline:User tries to register with invalid numbers when "<scenario>"
      Then I validate the presence of Login CTA popup
      When I click on the Login button
      Then it should show the Login text along with the enter mobile number textbox
      When I click on the Create an account link
      Then it should show the Signup texts in the page
      And I enter a phoneNo "<number>" in the phone number text box
      When I click on the Continue button
      Then it should shown an error message

      Examples:
      |scenario                               |number|
      |phone number having less than 10 digits|98363 |
      |phone number having 10 digits but starting with 0|0711725017|
      |phone number which does not exist                |1111122222|







