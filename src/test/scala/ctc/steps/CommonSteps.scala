package ctc.steps

import ctc.pages._
import ctc.utils.ScreenShotUtility
import cucumber.api.scala.EN
import cucumber.api.scala.ScalaDsl

class CommonSteps extends ScalaDsl with EN with ScreenShotUtility {

  Given("""^I clear my cookies""") {
    Page.clearCookies()
  }

  Given("""^I am on the Unloading remarks start page for Arrival Id (.*)$""") {
    (arrivalId: String) =>
      Page.goToUnloadingRemarksHomePage(arrivalId)
  }

  Given("""^I authenticate on (.+) page for Arrival Id (.*)$""") {
    (prettyUrl: String, arrivalId: String) =>
      Page.goToUnloadingRemarksRejectionPage(prettyUrl, arrivalId)
  }

  And("""^(?:I )?enter (.+) on the (.+) page$""") {
    (answer: String, url: String) =>
      Page.accessibilityCheck()
      Page.submitValuePage(url, answer)
  }

  When("""^(?:I )?answer ([Yy]es|[Nn]o) on the (.+) page$""") {
    (answer: String, prettyUrl: String) =>
      Page.accessibilityCheck()
      Page.submitYesNoPage(prettyUrl, answer)
  }

  When("""^(?:I )?input today's date on the (.+) page$""") {
    (prettyUrl: String) =>
      Page.accessibilityCheck()
      Page.submitDateNowPage(prettyUrl)
  }

  Then("""^(?:I )?(?:should )?be on the (.+) page$""") {
    prettyUrl: String =>
      Page.urlShouldMatch(prettyUrl)
  }

  And("""^(?:I )?clicked the (.+) button$""") {
    submit: String =>
      Page.clickById(submit)
  }

  When("""^(?:I )?click the (.+) link$""") {
    linkText: String =>
      Page.clickByLinkText(linkText)
  }

  And("""^(?:I )?click on change (.+)""") {
    page: String =>
      val id       = page.replace(" ", "-").toLowerCase
      val actualID = s"change-$id"
      Page.clickById(actualID)
  }

  When("""(?:I )?click on (.+) for item (.+)""") {
    (action: String, index: Int) =>
      val updateIndex = index - 1
      val id          = s"${action.replace(" ", "-")}-$updateIndex"
      Page.clickById(id)
  }

}
