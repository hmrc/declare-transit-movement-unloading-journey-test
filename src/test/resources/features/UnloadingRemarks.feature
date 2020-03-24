@unloading_remarks

Feature: Ability to submit unloading remarks

  Background: Trader selects to add unloading remarks

    Given I clear my cookies
    Given I am authenticated with user 1234567890
    And I am on the Unloading remarks start page

  Scenario: Simple unloading remark with no seal changes
    
    When I clicked the submit button
    Then I should be on the date goods unloaded page
    When I input date 24/03/2020 on the date goods unloaded page