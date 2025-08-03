Feature: User Login Functionality

  Scenario: Successful Login with Valid Credentials
    Given User is on the Flight Booking login page
    When User enters username "flightadmin" and password "flightadmin"
    And User enters the displayed security code
    And User clicks on the Validate button
    And User accepts the first alert
    And User clicks on the Login button
    And User accepts the second alert
    Then User should be redirected to the Flight Booking dashboard

  
  Scenario Outline: Failed Login with Invalid Credentials
    Given User is on the Flight Booking login page
    When User enters username "<username>" and password "<password>"
    And User enters the displayed security code
    And User clicks on the Validate button
    And User accepts the first alert
    And User clicks on the Login button
    Then User should see an error message "Please check the values and enter valid ones."

    Examples:
  | username       | password      | expected_message          |
  | wronguser      | wrongpass     | Username is wrong         |
  | flightadmin    | invalidpass   | Password is wrong         |
  | invaliduser    | flightadmin   | Username is wrong         |
  | ""             | flightadmin   | Username cannot be empty  |
  | flightadmin    | ""            | Password cannot be empty  |