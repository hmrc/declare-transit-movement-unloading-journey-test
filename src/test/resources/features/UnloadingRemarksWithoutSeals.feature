@unloading_remarks

Feature: Ability to submit unloading remarks without seals

  Background: Trader selects to add unloading remarks

    Given I clear my cookies

  Scenario: 1 - Unloading remarks without seals, with no changes to report

    And I am on the Unloading remarks start page for Arrival Id 5
    When I clicked the submit button
    Then I should be on the date goods unloaded page
    When I input today's date on the Date goods unloaded page
    Then I should be on the unloading summary page
    When I clicked the submit button
    Then I should be on the check your answers page
    When I clicked the submit button
    Then I should be on the confirmation page

