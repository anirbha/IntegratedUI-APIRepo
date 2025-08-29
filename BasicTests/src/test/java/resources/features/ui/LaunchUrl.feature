Feature: Launch the URL and validate the title of the page

  Background:
    Given as a user I launch the website

    @LaunchUrl
    Scenario: Url launch and validate the title header
      Then I validate the title of the page