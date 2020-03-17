
Feature: This Feature file validates and automates the DLS web interface

  Scenario: Verify Drone Schedules is displayed based on order numbers
    Given A sample file is uploaded with format Order Number, Location and Date Ordered
    Then Its Should generate the a output file with format Order Number and Drone Departure time with final record NPS Value


  Scenario: Error Handling in DLS interface with invalid Order Number
    Given A sample file is uploaded with invalid Order number is uploaded in the DLS Interface
    Then It Should display a message "Invalid Order Number format"

  Scenario: Error Handling in DLS interface with invalid Location
    Given A sample file is uploaded with invalid Location is uploaded in the DLS Interface
    Then It Should display a message "Invalid Location format"

  Scenario: Error Handling in DLS interface with invalid Date Format
    Given A sample file is uploaded with invalid Date is uploaded in the DLS Interface
    Then It Should display a message "Invalid Date format"


  Scenario: Validate Drone Schedules is displayed based on order numbers
    Given A sample file is uploaded with format Order Number, Location and Date Ordered
    Then validate generated output file values with sample output file


  Scenario: Check the drone departure time
    Given A Order Number is given to the DLS Interface
    Then It should display the drone delivery for that order