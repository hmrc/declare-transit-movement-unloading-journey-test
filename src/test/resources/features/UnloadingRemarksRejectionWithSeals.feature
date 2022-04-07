@unloading_remarks

Feature: Trader view and fix errors for Unloading Remarks rejections

  Background: Trader clicks to view arrival notifications
    Given I clear my cookies

  Scenario: 1 - Trader resubmits the Unloading Remarks to fix error for vehicle registration number
    When I authenticate on Unloading rejection page for Arrival Id 8
    Then I should be on the Unloading rejection page
    When I click on change vehicle registration rejection
    Then I should be on the vehicle name registration rejection page
    And I enter test data on the vehicle name registration rejection page
    Then I should be on the rejection check your answers page
    And I clicked the submit button
    Then I should be on the confirmation page

  Scenario: 2 - Trader resubmits the Unloading Remarks to fix error for date goods unloaded
    When I authenticate on Unloading rejection page for Arrival Id 9
    Then I should be on the Unloading rejection page
    When I click on change date goods unloaded
    Then I should be on the date goods unloaded rejection page
    And I input today's date on the date goods unloaded rejection page
    Then I should be on the rejection check your answers page
    And I clicked the submit button
    Then I should be on the confirmation page

  Scenario: 3 - Trader resubmits the Unloading Remarks to fix multiple errors
    When I authenticate on Unloading rejection page for Arrival Id 10
    Then I should be on the Unloading rejection page
    When I click the send new unloading remarks with the right information link
    Then I should be on the Unloading guidance page