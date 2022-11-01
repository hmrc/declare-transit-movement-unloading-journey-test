/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ctc.pages

import ctc.driver.Driver
import ctc.utils.{Configuration, UrlHelperWithHyphens}
import org.openqa.selenium._
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import java.time.LocalDate

object Page extends Page

trait Page {

  private val config: Configuration = Configuration()

  implicit val webDriver: WebDriver = Driver.webDriver

  private def waitFor(predicate: WebDriver => Boolean): Boolean =
    new WebDriverWait(webDriver, config.duration).until { (wd: WebDriver) =>
      predicate(wd)
    }

  private def waitForElement(by: By): WebElement =
    new WebDriverWait(webDriver, config.duration).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  private def bringIntoView(by: By, action: WebElement => Unit): Unit = {
    val element                 = waitForElement(by)
    val jse: JavascriptExecutor = webDriver.asInstanceOf[JavascriptExecutor]
    jse.executeScript("arguments[0].scrollIntoView()", element)
    action(element)
  }

  def clearCookies(): Unit = webDriver.manage().deleteAllCookies()

  private def click(by: By): Unit = bringIntoView(by, _.click)

  def clickById(id: String): Unit = click(By.id(id))

  def clickByLinkText(linkText: String): Unit = click(By.linkText(linkText))

  private def clickByCssSelector(cssSelector: String): Unit = click(By.cssSelector(cssSelector))

  private def clickSubmit(): Unit = clickById("submit")

  private def clearInputById(id: String): Unit = bringIntoView(By.id(id), _.clear())

  private def fillInput(by: By, text: String): Unit = {
    val input = webDriver.findElement(by)
    input.clear()
    if (text != null && text.nonEmpty) input.sendKeys(text)
  }

  private def fillInputById(id: String, text: String): Unit = fillInput(By.id(id), text)

  def urlShouldMatch(prettyUrl: String): Boolean = {
    val convertedUrl = UrlHelperWithHyphens.convertToUrlSlug(prettyUrl)
    waitFor(_.getCurrentUrl.toLowerCase.endsWith(convertedUrl.toLowerCase))
    waitForElement(By.cssSelector("h1")).isDisplayed
  }

  def urlShouldContain(prettyUrl: String): Boolean =
    waitFor(_.getCurrentUrl.toLowerCase.contains(prettyUrl))

  def authenticate(arrivalId: String, rejectionJourney: Boolean = false): Unit = {
    webDriver.navigate().to(config.authLoginUrl)
    val url         = s"${config.applicationsBaseUrl}/$arrivalId"
    val redirectUrl = if (rejectionJourney) s"$url/unloading-rejection" else url
    fillInput(By.cssSelector("*[name='redirectionUrl']"), redirectUrl)
    enterEnrolment(0, "HMRC-CTC-ORG", "EORINumber", "123456789")
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
    webDriver.navigate().to(config.authLoginUrl)
    val redirectUrl = s"${config.applicationsBaseUrl}/8/unloading-rejection"
    fillInput(By.cssSelector("*[name='redirectionUrl']"), redirectUrl)

    enrolmentType match {
      case "legacy" =>
        enterEnrolment(0, "HMCE-NCTS-ORG", "VATRegNoTURN", "123456789")
      case "dual"   =>
        enterEnrolment(0, "HMCE-NCTS-ORG", "VATRegNoTURN", "123456789")
        enterEnrolment(1, "HMRC-CTC-ORG", "EORINumber", "123456789")
      case "empty"  =>
        ()
    }

    clickByCssSelector("*[type='submit']")
  }

  private def enterEnrolment(index: Int, enrolment: String, identifierName: String, identifierValue: String): Unit = {
    fillInput(By.cssSelector(s"*[name='enrolment[$index].name']"), enrolment)
    fillInput(By.cssSelector(s"*[name='enrolment[$index].taxIdentifier[0].name']"), identifierName)
    fillInput(By.cssSelector(s"*[name='enrolment[$index].taxIdentifier[0].value']"), identifierValue)
  }

  def submitValuePage(url: String, answer: String): Unit = {
    urlShouldMatch(url)
    fillInputById("value", answer)
    clickSubmit()
  }

  def submitYesNoPage(prettyUrl: String, answer: String, baseId: String = "value"): Unit = {
    urlShouldMatch(prettyUrl)
    answer match {
      case "Yes" => clickById(s"$baseId")
      case "No"  => clickById(s"$baseId-${answer.toLowerCase}")
    }
    clickSubmit()
  }

  def submitDateValuePage(
    prettyUrl: String,
    day: String,
    month: String,
    year: String,
    baseId: String = "value"
  ): Unit = {
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

  def accessibilityCheck(): Unit =
    if (System.getProperty("a11y") == "true") {
      lazy val hasErrors   = webDriver.findElements(By.id("error-summary-title")).size() > 0
      lazy val isChangeUrl = webDriver.getCurrentUrl.contains("/change-")
      if (!hasErrors && !isChangeUrl) {
        clickSubmit()
      }
    }

  def clear(): Unit =
    clearInputById("value")
}
