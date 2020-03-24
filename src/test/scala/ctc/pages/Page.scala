package ctc.pages

import cucumber.api.DataTable
import ctc.driver.Driver
import ctc.models.FormInput
import ctc.utils.Configuration
import ctc.utils.ScreenShotUtility
import ctc.utils.UrlHelperWithHyphens
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium._
import org.openqa.selenium.interactions.Actions
import org.scalatest._
import scala.collection.JavaConverters._

object Page extends Page

trait Page extends Matchers with ScreenShotUtility {

  implicit val webDriver: WebDriver = Driver.webDriver

  def pageTitle = webDriver.getTitle

  protected def waitFor(predicate: WebDriver => Boolean): Boolean =
    new WebDriverWait(webDriver, Configuration.settings.timeout).until {
      new ExpectedCondition[Boolean] {
        override def apply(wd: WebDriver) = predicate(wd)
      }
    }

  def waitForElement(by: By) =
    new WebDriverWait(webDriver, Configuration.settings.timeout).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  def waitForPageToLoad(by: By) =
    new FluentWait(webDriver).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  protected def bringIntoView(id: String, action: WebElement => Unit) = {
    val element                  = waitForElement(By.id(id))
    val jse2: JavascriptExecutor = webDriver.asInstanceOf[JavascriptExecutor]
    jse2.executeScript("arguments[0].scrollIntoView()", element)
    action(element)
  }

  def clearCookies() = webDriver.manage().deleteAllCookies()

  def click(by: By) = webDriver.findElement(by).click()

  def clickById(id: String) = click(By.id(id))

  def clickByLinkText(linkText: String) = click(By.linkText(linkText))

  def clickByPartialLinkText(linkText: String) = click(By.partialLinkText(linkText))

  def clickByCssSelector(cssSelector: String) = click(By.cssSelector(cssSelector))

  def clickByTagName(tagName: String) = click(By.tagName("button"))

  def clickSubmit() =
    bringIntoView("submit", {
      e =>
        e.click()
    })

  def findInputByValue(value: String): WebElement =
    webDriver.findElement(By.cssSelector(s"""input[value="$value"]"""))

  def fillInput(by: By, text: String) = {
    val input = webDriver.findElement(by)
    input.clear()
    if (text != null && text.nonEmpty) input.sendKeys(text)
  }

  def fillInputById(id: String, text: String) = fillInput(By.id(id), text)

  def findByLinkText(text: String) = webDriver.findElement(By.linkText(text))

  def urlShouldMatch(prettyUrl: String) = {

    val convertedUrl = UrlHelperWithHyphens.convertToUrlSlug(prettyUrl)
    waitFor(wd => wd.getCurrentUrl.toLowerCase.endsWith(convertedUrl.toLowerCase))

    waitForElement(By.cssSelector("h1")).isDisplayed()
  }

  def toClear(id: String) = webDriver.findElement(By.id(id)).clear()

  def goToManageHomePage(): Unit = {
    val url = s"${Configuration.settings.manageBaseUrl}"
    webDriver.navigate().to(url)
    urlShouldMatch("manage-transit-movements")
  }

  def goToAuthPage() = {
    val authUrl = s"${Configuration.settings.authLoginUrl}"
    webDriver.navigate().to(authUrl)
  }

  def authenticate(id: String) = {
    goToAuthPage()
    val redirectUrl = s"${Configuration.settings.applicationsBaseUrl}/movement-reference-number"
    fillInput(By.cssSelector("*[name='redirectionUrl']"), redirectUrl)
    fillInput(By.cssSelector("*[name='enrolment[1].name']"), "HMCE-NCTS-ORG")
    fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].name']"), "VATRegNoTURN")
    fillInput(By.cssSelector("*[name='enrolment[1].taxIdentifier[0].value']"), id)
    clickByCssSelector("*[type='submit']")
  }

  def goToArrivalHomePage(): Unit = {
    val url = s"${Configuration.settings.applicationsBaseUrl}/movement-reference-number"
    webDriver.navigate().to(url)
    urlShouldMatch("movement-reference-number")
  }

  def submitValuePage(url: String, answer: String) = {
    urlShouldMatch(url)
    fillInputById("value", answer)
    clickById("submit")
  }

  def changeValuePage(url: String, answer: String) = {
    urlShouldMatch(url)
    toClear("value")
    fillInputById("value", answer)
    clickById("submit")
  }

  def submitSelectPage(url: String, answer: String) = {
    urlShouldMatch(url)

    webDriver.findElement(By.id("value")).sendKeys(answer)
    webDriver.findElement(By.id("value__option--0")).click()

    clickById("submit")
  }

  def submitYesNoPage(prettyUrl: String, answer: String, baseId: String = "value") = {

    urlShouldMatch(prettyUrl)
    answer match {
      case "Yes" => clickById(s"$baseId")
      case "No"  => clickById(s"$baseId-${answer.toLowerCase}")
    }
    clickSubmit()
  }

  def submitDateValuePage(prettyUrl: String, day: String, month: String, year: String, baseId: String = "value") = {
    urlShouldMatch(prettyUrl)
    fillInputById(s"${baseId}_day", day)
    fillInputById(s"${baseId}_month", month)
    fillInputById(s"${baseId}_year", year)
    clickSubmit()
  }

  def submitDataTablePage(prettyUrl: String, data: DataTable) = {
    urlShouldMatch(prettyUrl)
    val inputs = data.asList[FormInput](classOf[FormInput]).asScala
    for (input <- inputs) fillInputById(input.question, input.answer)
    clickSubmit()
  }

  def accessibilityCheck() = {
    lazy val prop = System.getProperty("a11y")
    if (prop == "true") {
      lazy val errors = webDriver.findElements(By.id("error-summary-title")).size() > 0
      lazy val url    = webDriver.getCurrentUrl.contains("/change-")
      if (errors == false && url == false) {
        clickSubmit()
      }
    }
  }

  def selectValueAutoComplete(url: String, answer: String) = {
    urlShouldMatch(url)
    fillInputById("value", answer)
    waitForElement(By.id("value"))
    clickByCssSelector("li#value__option--0")
    clickSubmit()

  }

  def changeValueAutoComplete(url: String, answer: String) = {
    urlShouldMatch(url)
    //This is required to clear any existing value in the autocomplete box
    new Actions(webDriver).click(webDriver.findElement(By.className("autocomplete__wrapper"))).sendKeys(Keys.DELETE).perform()
    val autoFill: WebElement               = webDriver.findElement(By.id("value__listbox"))
    val availableOptions: List[WebElement] = autoFill.findElements(By.tagName("li")).asScala.toList
    availableOptions.find(_.getText.contains(answer)) match {
      case Some(element) => element.click()
      case None          => throw new RuntimeException(s"$answer option not found in the auto-complete drop down in page:$url")
    }
    clickSubmit()
  }
}
