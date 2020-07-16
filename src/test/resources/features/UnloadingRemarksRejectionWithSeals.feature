@unloading_remarks

Feature: Trader view and fix errors for Unloading Remarks rejections

  Background: Trader clicks to view arrival notifications
    Given I clear my cookies

  Scenario: 1 - Trader resubmits the Unloading Remarks to fix errors for Unloading rejection errors
    When I authenticate on Unloading rejection page for Arrival Id 8
    Then I should be on the Unloading rejection page
    When I click on change vehicle registration rejection
    Then I should be on the vehicle name registration rejection page
    And I enter test data on the vehicle name registration rejection page
    Then I should be on the rejection check your answers page
    And I clicked the submit button
    Then I should be on the confirmation page

