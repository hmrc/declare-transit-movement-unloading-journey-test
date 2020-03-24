package ctc.steps

import ctc.pages._
import ctc.utils.ScreenShotUtility
import cucumber.api.DataTable
import cucumber.api.scala.EN
import cucumber.api.scala.ScalaDsl

class CommonSteps extends ScalaDsl with EN with ScreenShotUtility {

  Given("""^I clear my cookies""") {
    Page.clearCookies()
  }

  Given("""^I am on the Arrival notification start page$""") {
    Page.goToArrivalHomePage()
  }

  Given("""^I am on the home page$""") {
    Page.goToManageHomePage()
  }

  Given("""^I am authenticated with user (.*)$""") {
    (id: String) =>
      Page.authenticate(id)
  }

  And("""^(?:I )?enter (.+) on the (.+) page$""") {
    (answer: String, url: String) =>
      Page.accessibilityCheck()
      Page.submitValuePage(url, answer)
  }

  And("""^(?:I )?change the value to (.+) on the (.+) page$""") {
    (answer: String, url: String) =>
      Page.changeValuePage(url, answer)
  }

  And("""^(?:I )?select (.+) on the (.+) page$""") {
    (answer: String, url: String) =>
      Page.accessibilityCheck()
      Page.selectValueAutoComplete(url, answer)
  }

  And("""^(?:I )?change to (.+) on the (.+) page$""") {
    (answer: String, url: String) =>
      Page.accessibilityCheck()
      Page.changeValueAutoComplete(url, answer)
  }

  When("""^(?:I )?answer ([Yy]es|[Nn]o) on the (.+) page$""") {
    (answer: String, prettyUrl: String) =>
      Page.accessibilityCheck()
      Page.submitYesNoPage(prettyUrl, answer)
  }

  When("""^(?:I )?answer (A different container|A different vehicle|Both) on the (.+) page$""") {
    (answer: String, prettyUrl: String) =>
      Page.accessibilityCheck()
      TranshipmentTypePage.submitRadioPage(prettyUrl, answer)
  }

  When("""^(?:I )?enter a date of (\d+)/(\d+)/(\d+) on the (.+) page$""") {
    (day: String, month: String, year: String, title: String) =>
      Page.accessibilityCheck()
      Page.submitDateValuePage(title, day, month, year)
  }

  Then("""^(?:I )?(?:should )?be on the (.+) page$""") {
    prettyUrl: String =>
      Page.urlShouldMatch(prettyUrl)
  }

  And("""^(?:I )?clicked the (.+) button$""") {
    submit: String =>
      Page.clickById(submit)
  }

  When("""^(?:I )?fill in the (.+) page as follows$""") {
    (prettyUrl: String, dataTable: DataTable) =>
      Page.accessibilityCheck()
      Page.submitDataTablePage(prettyUrl, dataTable)
  }

  When("""^(?:I )?click the link with visible text: (.+)$""") {
    linkText: String =>
      Page.clickByPartialLinkText(linkText)
  }

  When("""^the (.*) link is present$""") {
    linkText: String =>
      Page.findByLinkText(linkText).getText == linkText
  }

  When("""^search for '(.+)'$""") {
    searchString: String =>
      Page.accessibilityCheck()
      Page.submitValuePage("search applications", searchString)
  }

  When("""^(?:I )?click the (.+) link$""") {
    linkText: String =>
      Page.clickByLinkText(linkText)
  }

  And("""^(?:I )?clicked (.+) on the (.+) page""") {
    (text: String, url: String) =>
      val id = text.toLowerCase().replace(" ", "-")
      Page.urlShouldMatch(url)
      Page.clickById(id)
  }
}
