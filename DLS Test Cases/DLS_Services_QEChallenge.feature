
Feature: This Feature file validates DLS core, NPS Engine, DDTC Engine, DLS Generator and DLS Access services in the
         DLS Micro service Layer

  Scenario: Verify DLS core is generating Drone Schedules
    Given A sample list of records in the format, Order Number, Location and Date Ordered
    Then  It should output records in the format, Order Number and Drone Departure time

  Scenario: Validate DLS Core Drone Schedules
    Given A sample list of records in the format, Order Number, Location and Date Ordered
    Then  Compare output records generated with a sample drone schedule data containing records with ordernumber and Departure time


  Scenario: Validate DLS Core Data formats
    Given A sample list of records in the format, Order Number, Location and Date Ordered
    Then  Validate the order number with format "WM###"
    And   Validate the Drone Departure Schedule with format "HH:MM:SS"


  Scenario Outline: Verify Drone Delivery Time Calculator (DDTC) Engine
    Given A sample location <Customer Location>
    Then It should calculate the <Delivery Time> for the order
    Examples:
      |Customer Location|Delivery Time|
      |N11W5            |16           |
      |S3E2             |5            |
      |N7E50            |57           |
      |N11E5            |16           |


    Scenario Outline: Verify Net Promoter Score (NPS) Engine's classification of customer type based on Delivery Time
      Given A <Delivery Time> in hrs to the NPS Engine
      Then It should validate the <Customer Type> based on Delivery Time
      Examples:
        |Delivery Time|Customer Type|
        |2            |Promoter     |
        |8            |Detractor    |
        |3            |Nuetral      |
        |1            |Promoter     |
        |6            |Detractor    |


    Scenario : Validate the NPS value from Net Promoter Score Engine based on Customer Type
        Given A Promoter Percentage and Detractor percentage
        Then It should calculate the NPS Value


    Scenario: Verify the NPS Value generated from Net Promoter Score Engine
        Given A list of Delivery Time and Customer Type
        Then It should generate a NPS Value based on the list


    Scenario: Verify DLS Generator output
      Given A sample list of records in the format Order Number, Location and Date Ordered
      Then It should output records in the format of, Order Number and Drone Departure time and NPS Value


    Scenario: Verify DLS Generator Error handling
      Given A sample list of records in the invalid format
      Then DLS Generator Should display the "Invalid Data"


    Scenario: Validate DLS Generator output
      Given A sample list of records in the format Order Number, Location and Date Ordered
      Then It should generate a valid output with records in the format of, Order Number and Drone Departure time and NPS Value


     Scenario: Verify DLS Access
       Given A Specific period of Time and Date
       Then It Should display all list of records in the format Order Number, Location and Date Ordered

     Scenario: Check Drone Delivery Time in DLS Access
       Given A Order number to the DLS Access
       Then Should display the Delivery time for that order


  Scenario: Verify DLS core is generating Drone given Order placed time is same
    Given A sample list of records in the format, Order Number, Location and Date Ordered with Same Order Dates
    Then  It should output records in the format, Order Number and Drone Departure time