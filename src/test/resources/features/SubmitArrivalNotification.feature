@arrival_submission

Feature: Ability to submit Normal arrival notification

  Background: Trader selects BorderForce and enter address details

    Given I clear my cookies
    Given I am authenticated with user 1234567890
    And I am on the Arrival notification start page
    And I enter 99IT9876AB88901209 on the Movement reference number page
    And I choose Border Force office on the Where are the goods page
    And I enter GB on the Goods approved location page
    And I select GB000060 on the Border force office page
    And I enter name on the Destination trader name page
    And I enter GB1234 on the Destination trader eori page
    And fill in the Destination trader address page as follows
      | Question          | Answer       |
      | buildingAndStreet | Baker street |
      | city              | London       |
      | postcode          | NW16XE       |

  Scenario: 1 - Trader submit Normal arrival notification without events on route with postcode as place of notification

    When I answer Yes on the Complete arrival at trader address page
    And I answer No on the Event on journey page
    And I should be on the Check your answers arrival page
    And I clicked the submit button
    Then I should be on the Arrival notification sent page

  Scenario: 2 - Trader submits arrival notification with event reported, with transhipment and have no seals changed

    When I answer No on the Complete arrival at trader address page
    And I enter place on the Place complete arrival page
    And I answer Yes on the Event on journey page
    And I select France on the Event country page
    And I enter Paris on the Event place page
    And I answer Yes on the Reported event page
    And I answer Yes on the Vehicle or container page
    And I answer Both on the What changed page
    And I enter 1 on the Container number page
    And I answer Yes on the Add container page
    And I enter 2 on the Container number page
    And I click on Remove for item 2 on the add container page
    And I answer Yes on the Confirm remove container page
    And I click on Change for item 1 on the add container page
    And I change the value to 5 on the Container number page
    And I answer No on the Add container page
    And I enter vehicle name on the Vehicle name registration reference page
    And I select Italy on the Vehicle registration country page
    And I answer No on the Seals changed page
    And I should be on the Check event answers page
    And I clicked the submit button
    And I answer No on the Add event page
    And I clicked the submit button
    Then I should be on the Arrival notification sent page


  Scenario: 3 - Trader submits arrival notification with event reported, with no transhipment and have seals changed

    When I answer No on the Complete arrival at trader address page
    And I enter place on the Place complete arrival page
    And I answer Yes on the Event on journey page
    And I select France on the Event country page
    And I enter Paris on the Event place page
    And I answer Yes on the Reported event page
    And I answer No on the Vehicle or container page
    And I answer Yes on the Seals changed page
    And I enter 1 on the Seal number page
    And I answer Yes on the Add seal page
    And I enter 2 on the Seal number page
    And I click on Remove for item 2 on the add Seal page
    And I answer Yes on the Confirm remove seal page
    And I click on Change for item 1 on the add Seal page
    And I change the value to 5 on the Seal number page
    And I answer No on the Add seal page
    And I should be on the Check event answers page
    And I clicked the submit button
    And I answer No on the Add event page
    And I clicked the submit button
    Then I should be on the Arrival notification sent page


  Scenario: 4 - Trader submits arrival notification with event not reported, with no transhipment, have no seals changed and add event

    When I answer No on the Complete arrival at trader address page
    And I enter place on the Place complete arrival page
    And I answer Yes on the Event on journey page
    And I select France on the Event country page
    And I enter Paris on the Event place page
    And I answer No on the Reported event page
    And I answer No on the Vehicle or container page
    Then I should be on the Event Details page
    And I enter Incident details on the Event Details page
    And I answer No on the Seals changed page
    And I should be on the Check event answers page
    And I clicked the submit button
    And I answer Yes on the Add event page
    And I select Italy on the Event country page
    And I enter Rome on the Event place page
    And I answer Yes on the Reported event page
    And I answer No on the Vehicle or container page
    And I answer No on the Seals changed page
    And I should be on the Check event answers page
    And I clicked the submit button
    And I click on Remove for item 2 on the add Event page
    And I answer Yes on the Confirm remove event page
    And I click on Change for item 1 on the add Event page
    And I click on Change country on Check event 1 answers page
    And I change to Belgium on the Event country page
    Then I should be on the Check event answers page
    And I clicked the submit button
    And I answer No on the Add event page
    And I should be on the Check your answers arrival page
    And I click on change presentation office
    And I change to GB000244 on the Border force office page
    And I clicked the submit button
    And I should be on the Arrival notification sent page
    And I clicked Manage Transit Movements on the Arrival notification sent page
    Then I am on the home page
