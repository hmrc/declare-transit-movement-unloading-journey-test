@unloading_remarks

Feature: Ability to submit unloading remarks with seals

  Background: Trader selects to add unloading remarks

    Given I clear my cookies
    And I am on the Unloading remarks start page for Arrival Id 2
    When I clicked the submit button
    Then I should be on the Date goods unloaded page
    When I input today's date on the Date goods unloaded page
    When I answer Yes on the can seals be read page
    When I answer No on the are any seals broken page
    Then I should be on the unloading summary page

  Scenario: 1 - Unloading remarks with seals, no changes to seals but have other changes to report

    When I click the Add comment link
    And I enter stowaways found on the changes to report page
    Then I should be on the unloading summary page
    When I clicked the remove-comment button
    When I answer No on the confirm remove comments page
    Then I should be on the unloading summary page
    When I clicked the submit button
    Then I should be on the check your answers page
    When I clicked the submit button
    Then I should be on the confirmation page

  Scenario: 2 - Unloading remarks with changes to summary answers
    
    When I click on change vehicle reference
    And I enter NE20 CTC on the change vehicle name registration reference page
    Then I should be on the unloading summary page
    When I clicked the submit button
    Then I should be on the check your answers page
    When I click on change date goods unloaded
    And I input date 15/04/2020 on the date goods unloaded page
    Then I should be on the check your answers page
    When I click on change answers
    Then I should be on the unloading summary page
    When I click on change gross mass
    And I enter 1500 on the change gross mass amount page
    Then I should be on the unloading summary page
    When I click on change total number of items
    And I enter 150 on the change total number of items page
    Then I should be on the unloading summary page
    When I click on change total number of packages
    And I enter 150 on the change total number of packages page
    Then I should be on the unloading summary page
    When I clicked the submit button
    Then I should be on the check your answers page
    When I clicked the submit button
    Then I should be on the confirmation page

  @a11y
  Scenario: 3 - Unloading Remarks with seals, adds new seals and changes the check seals section from CYA page

    When I click the Add a new seal number link
    And I enter Seal123xyz found on the new seal number page
    Then I should be on the unloading summary page
    When I click on remove seal for item 4
    And I answer Yes on the confirm remove seal page
    Then I should be on the unloading summary page
    When I clicked the submit button
    Then I should be on the check your answers page
    When I click on change can seals be read
    And I answer No on the change can seals be read page
    Then I should be on the check your answers page
    When I click on change are any seals broken
    And I answer Yes on the change are any seals broken page
    Then I should be on the check your answers page
    When I clicked the submit button
    Then I should be on the confirmation page
