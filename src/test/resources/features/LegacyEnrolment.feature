@legacyenrolment
Feature: Ability to enrol with the legacy NCTS enrolment method

  Background:
    Given I clear my cookies

  Scenario: New CTC enrolment
    When I authenticate on Unloading rejection page for Arrival Id 8
    Then I should be on the Unloading rejection page

  Scenario: Legacy NCTS enrolment
    Given I authenticate on Unloading rejection page with an enrolment as legacy
    Then I should be on the Unloading rejection page

  Scenario: Both enrolments available
    Given I authenticate on Unloading rejection page with an enrolment as dual
    Then I should be on the Unloading rejection page

  Scenario: No enrolment available
    Given I authenticate on Unloading rejection page with an enrolment as empty
    When I verify the url contains customs-enrolment-services/ctc/subscribe
