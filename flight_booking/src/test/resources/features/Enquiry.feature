Feature: Enquiry Page Form Submission

As a user, I want to submit an enquiry form
So that I can get a response from the company

Scenario Outline: Submit enquiry form with different valid data 
Given User is on the Enquiry page 
When User enters enquiry details 
| Field         | Value           | 
| FULL NAME     | <Name>          | 
| EMAIL ADDRESS | <Email>         | 
| PHONE NUMBER  | <Phone>         | 
| SUBJECT       | <Subject>       | 
| MESSAGE       | <Message>       | 
And User clicks the Send button 
Then User should see a confirmation message starting with "THANK YOU!"

Examples:
  | Name          | Email                 | Phone       | Subject             | Message                     |
  | John Doe      | john.doe@example.com  | 1234567890  | General Enquiry     | I have a question.          |
  | Jane Smith    | jane.smith@example.com| 98765432101 | Booking Issue       | My booking is not showing.  |
  | Peter Jones   | peter.j@test.co       | 5551234567  | Feedback            | Hi I have problem           |
