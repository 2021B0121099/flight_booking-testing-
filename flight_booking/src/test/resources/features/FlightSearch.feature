Feature: Flight Search by Specific Criteria

  As a user, I want to search for flights using different criteria
  So that I can quickly find the flight I am looking for

  Scenario Outline: Search for flights using different criteria
    Given User is on the Flight Search page
    When User searches for "<Search By>" with value "<Search Value>"
    And User clicks on the corresponding search button
    

    Examples:
      | Search By           | Search Value    |
      | Flight Number       | AC789           |
      | Flight Name         | SkyRider Express|
      | Flight Type         | Direct          |
      | Flight Number       | UK-999          |
      