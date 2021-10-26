package ctc.pages

import ctc.driver.Driver
import ctc.utils.Configuration
import ctc.utils.ScreenShotUtility
import ctc.utils.UrlHelperWithHyphens
import org.openqa.selenium._
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.scalatest._

import java.time.LocalDate

object Page extends Page

trait Page extends Matchers with ScreenShotUtility {

  implicit val webDriver: WebDriver = Driver.webDriver

  private def waitFor(predicate: WebDriver => Boolean): Boolean =
    new WebDriverWait(webDriver, Configuration.settings.timeout).until {
      (wd: WebDriver) =>
        predicate(wd)
    }

  private def waitForElement(by: By): WebElement =
    new WebDriverWait(webDriver, Configuration.settings.timeout).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  private def bringIntoView(id: String, action: WebElement => Unit): Unit = {
    val element                  = waitForElement(By.id(id))
    val jse2: JavascriptExecutor = webDriver.asInstanceOf[JavascriptExecutor]
    jse2.executeScript("arguments[0].scrollIntoView()", element)
    action(element)
  }

  def clearCookies(): Unit = webDriver.manage().deleteAllCookies()

  private def click(by: By): Unit = webDriver.findElement(by).click()

  def clickById(id: String): Unit = click(By.id(id))

  def clickByLinkText(linkText: String): Unit = click(By.linkText(linkText))

  private def clickByCssSelector(cssSelector: String): Unit = click(By.cssSelector(cssSelector))

  private def clickSubmit(): Unit =
    bringIntoView("submit", {
      e =>
        e.click()
    })

  private def fillInput(by: By, text: String): Unit = {
    val input = webDriver.findElement(by)
    input.clear()
    if (text != null && text.nonEmpty) input.sendKeys(text)
  }

  private def fillInputById(id: String, text: String): Unit = fillInput(By.id(id), text)

  def urlShouldMatch(prettyUrl: String): Boolean = {
    val convertedUrl = UrlHelperWithHyphens.convertToUrlSlug(prettyUrl)
    waitFor(wd => wd.getCurrentUrl.toLowerCase.endsWith(convertedUrl.toLowerCase))

    waitForElement(By.cssSelector("h1")).isDisplayed
  }

  def authenticate(arrivalId: String, rejectionJourney: Boolean = false): Unit = {
    webDriver.navigate().to(Configuration.settings.authLoginUrl)
    val url         = s"${Configuration.settings.applicationsBaseUrl}/$arrivalId"
    val redirectUrl = if (rejectionJourney) s"$url/unloading-rejection" else url
    fillInput(By.cssSelector("*[name='redirectionUrl']"), redirectUrl)
    fillInput(By.cssSelector("*[name='enrolment[1].name']"), "HMRC-CTC-ORG")
    fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].name']"), "EORINumber")
    fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].value']"), "123456789")
    clickByCssSelector("*[type='submit']")
  }

  def goToUnloadingRemarksHomePage(arrivalId: String): Unit = {
    authenticate(arrivalId)
    urlShouldMatch("unloading-guidance")
  }

  def goToUnloadingRemarksRejectionPage(url: String, arrivalId: String): Unit = {
    authenticate(arrivalId, rejectionJourney = true)
    urlShouldMatch(url)
  }

  def authenticateEnrolment(enrolmentType: String, rejectionJourney: Boolean = false): Unit = {
    webDriver.navigate().to(Configuration.settings.authLoginUrl)
    val redirectUrl = s"${Configuration.settings.applicationsBaseUrl}/8/unloading-rejection"
    fillInput(By.cssSelector("*[name='redirectionUrl']"), redirectUrl)

    val enrolmentKey: Unit = enrolmentType match {
      case "legacy" =>
        fillInput(By.cssSelector("*[name='enrolment[1].name']"), "HMCE-NCTS-ORG")
        fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].name']"), "VATRegNoTURN")
        fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].value']"), "123456789")

      case "dual" =>
        fillInput(By.cssSelector("*[name='enrolment[1].name']"), "HMCE-NCTS-ORG")
        fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].name']"), "VATRegNoTURN")
        fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].value']"), "123456789")

        fillInput(By.cssSelector("*[name='enrolment[2].name']"), "HMRC-CTC-ORG")
        fillInput(By.cssSelector("*[name='enrolment[2].taxIdentifier[0].name']"), "EORINumber")
        fillInput(By.cssSelector("*[name='enrolment[2].taxIdentifier[0].value']"), "123456789")

      case "empty" =>
    }

    clickByCssSelector("*[type='submit']")
  }

  def submitValuePage(url: String, answer: String): Unit = {
    urlShouldMatch(url)
    fillInputById("value", answer)
    clickById("submit")
  }

  def submitYesNoPage(prettyUrl: String, answer: String, baseId: String = "value"): Unit = {
    urlShouldMatch(prettyUrl)
    answer match {
      case "Yes" => clickById(s"$baseId")
      case "No"  => clickById(s"$baseId-${answer.toLowerCase}")
    }
    clickSubmit()
  }

  def submitDateValuePage(prettyUrl: String, day: String, month: String, year: String, baseId: String = "value"): Unit = {
    urlShouldMatch(prettyUrl)
    fillInputById(s"$baseId", day)
    fillInputById(s"${baseId}_month", month)
    fillInputById(s"${baseId}_year", year)
    clickSubmit()
  }

  def submitDateNowPage(prettyUrl: String, baseId: String = "value"): Unit = {
    val now = LocalDate.now()
    submitDateValuePage(
      prettyUrl = prettyUrl,
      day = s"${now.getDayOfMonth}",
      month = s"${now.getMonthValue}",
      year = s"${now.getYear}",
      baseId = baseId
    )
  }

  def accessibilityCheck(): Unit = {
    lazy val prop = System.getProperty("a11y")
    if (prop == "true") {
      lazy val hasErrors   = webDriver.findElements(By.id("error-summary-title")).size() > 0
      lazy val isChangeUrl = webDriver.getCurrentUrl.contains("/change-")
      if (!hasErrors && !isChangeUrl) {
        clickSubmit()
      }
    }
  }

}
