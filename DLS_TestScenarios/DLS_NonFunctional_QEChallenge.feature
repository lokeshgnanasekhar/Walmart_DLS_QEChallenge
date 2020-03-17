Feature: This Feature file provides various non functional scenarios for Drone Launch Scheduling Program

  Scenario: Load test the DLS Core
    Given A large amount of Order records to DLS core
    Then It should generate the Departure Times for those orders

  Scenario: validate Authorization for DLS Core Services
      Given  A request made to DLS core Services
      Then   the request is processed once it is authorized properly

  Scenario: validate Authorization for DDTC  Services
    Given  A request made to DDTC  Services
    Then   the request is processed once it is authorized properly

  Scenario: validate Authorization for NPS Engine  Services
    Given  A request made to NPS Engine Services
    Then   the request is processed once it is authorized properly

  Scenario: validate Authorization for DLS Generator Services
    Given  A request made to DLS Generator Services
    Then   the request is processed once it is authorized properly

  Scenario: validate Authorization for DLS Access Services
    Given  A request made to DLS Access Services
    Then   the request is processed once it is authorized properly

  Scenario: Verfiy DLS Micro Services under Stress
    Given  A request made to DLS Micro Services parallely from many interfaces
    Then   the request is processed and drone schedules are handled

  Scenario: Verfiy Localization on DLS Web Interface
    Given  A Sample input file with localized time is given to interface
    Then   DLS Web interface should convert those timings and generate a output flile with proper format

  Scenario: Verify Failover scenarios for DLS Micro services

  Scenario: Verify the Services are in proper usability format for different interface application

  Scenario: Verify Authorization for DLS Web Interface

