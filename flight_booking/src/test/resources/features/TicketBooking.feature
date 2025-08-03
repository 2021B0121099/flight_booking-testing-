Feature: Flight Booking Input Validation

As a user, I want to be informed of incorrect data
So that I can enter the correct information to book my ticket

  Scenario Outline: Validate booking inputs and error messages
    Given User is on the Flight Ticket Booking page
    When User enters the following booking details:
      | Field                  | Value                    |
      | Travel From            | <Travel From>            |
      | Travel To              | <Travel To>              |
      | Departure Date         | <Departure Date>         |
      | Class                  | <Class>                  |
      | Passenger Name         | <Passenger Name>         |
      | Email                  | <Email>                  |
      | Phone Number           | <Phone Number>           |
      | Number of Passengers   | <Number of Passengers>   |
    And User clicks on the "Book Now" button
    Then User should see expected message "<Expected Message>"

    Examples:
      | Travel From | Travel To | Departure Date | Class | Passenger Name | Email | Phone Number | Number of Passengers | Expected Message |
      | Delhi | Mumbai | 2025-09-15 | Economic Class | John Doe | john.doe@example.com | 9876543210 | 2 | Your flight Reservation has been Confirmed ! |
      | Delhi | Mumbai | 2025-09-15 | First Class | John Doe | invalid-email | 9876543210 | 1 | Please enter a valid email. |
      | Delhi | Mumbai | 2024-01-01 | Luxury Class | John Doe | john.doe@example.com | 9876543210 | 1 | Departure date cannot be in the past. |
      

  Scenario Outline: Verify Class Selection
    Given User is on the Flight Ticket Booking page
    When User enters valid booking details for "<Class>"
    And User clicks on the "Book Now" button
    Then User should see a confirmation message for class "<Class>"

    Examples:
      | Class |
      | Luxury Class |
      | First Class |
      | Economic Class |
