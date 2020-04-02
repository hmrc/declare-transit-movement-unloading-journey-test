@unloading_remarks

Feature: Ability to submit unloading remarks

  Background: Trader selects to add unloading remarks

    Given I clear my cookies
    And I am on the Unloading remarks start page for MRN 19IT02110010007827

  Scenario: Simple unloading remark with no seal changes, but have changes to report

    When I clicked the submit button
    Then I should be on the date goods unloaded page
    When I input date 24/03/2020 on the date goods unloaded page
    When I answer Yes on the can seals be read page
    When I answer No on the are any seals broken page
    Then I should be on the unloading summary page
    When I clicked the submit button
    When I answer Yes on the anything else to report page
    When I enter stowaways found on the changes to report page
    Then I should be on the check your answers page
    When I clicked the submit button
    Then I should be on the confirmation page

  Scenario: Simple unloading remark with changes to summary answers

    When I clicked the submit button
    Then I should be on the date goods unloaded page
    When I input date 24/03/2020 on the date goods unloaded page
    When I answer Yes on the can seals be read page
    When I answer No on the are any seals broken page
    Then I should be on the unloading summary page
    When I click on change vehicle reference
    And I enter NE20 CTC on the change vehicle name registration reference page
    Then I should be on the unloading summary page
    When I click on change gross mass
    And I enter 1500 on the change gross mass amount page
    Then I should be on the unloading summary page
    

