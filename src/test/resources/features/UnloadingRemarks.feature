@unloading_remarks

Feature: Ability to submit unloading remarks

  Background: Trader selects to add unloading remarks

    Given I clear my cookies

  Scenario: Simple unloading remark with seals, no changes to seals but have other changes to report

    And I am on the Unloading remarks start page for MRN 19IT02110010007827
    When I clicked the submit button
    Then I should be on the date goods unloaded page
    When I input date 24/03/2020 on the date goods unloaded page
    When I answer Yes on the can seals be read page
    When I answer No on the are any seals broken page
    Then I should be on the unloading summary page
    Then I click the Add comment link
    When I enter stowaways found on the changes to report page
    Then I should be on the unloading summary page
    When I click the Remove link
    When I answer No on the confirm remove comment page
    Then I should be on the unloading summary page
    When I clicked the submit button
    Then I should be on the check your answers page
    When I clicked the submit button
    Then I should be on the confirmation page

  Scenario: Simple unloading remark with changes to summary answers

    And I am on the Unloading remarks start page for MRN 19IT02110010007827
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

  Scenario: Simple unloading remark without seals, with changes to report

    And I am on the Unloading remarks start page for MRN 99IT9876AB88901209
    When I clicked the submit button
    Then I should be on the date goods unloaded page
    When I input date 24/03/2020 on the date goods unloaded page
    Then I should be on the unloading summary page
    When I clicked the submit button
    Then I should be on the check your answers page
    When I clicked the submit button
    Then I should be on the confirmation page
    

