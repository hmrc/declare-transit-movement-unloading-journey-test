@unloading_remarks

Feature: Ability to submit unloading remarks

  Background: Trader selects to add unloading remarks

    Given I clear my cookies
    Given I am authenticated with user 1234567890
    And I am on the Unloading start page

  Scenario: 1 - TBC

    When I answer Yes on the Complete arrival at trader address page
    And I answer No on the Event on journey page
    And I should be on the Check your answers arrival page
    And I clicked the submit button
    Then I should be on the Arrival notification sent page
