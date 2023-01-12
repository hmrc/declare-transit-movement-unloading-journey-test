/*
 * Copyright 2023 HM Revenue & Customs
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

package ctc.utils

import ctc.driver.Driver
import io.cucumber.scala.Scenario
import org.apache.commons.io.FileUtils
import org.junit.Before
import org.openqa.selenium._

import java.io.File
import java.util.Calendar

object ScreenShotMeta {
  var scenario: Scenario   = null
  def scenarioName: String = scenario.getName.replace(" ", "_") + "@" + scenario.getStatus
}

trait ScreenShotUtility {

  @Before
  def grabScenario(scenario: Scenario): Unit =
    ScreenShotMeta.scenario = scenario

  val screenCapture: String = System.getProperty("screenshots")
  val welshVersion: String  = System.getProperty("switchToWelsh")

  def tryTakeScreenShot(): Unit =
    if (screenCapture == "true") {
      val wd: WebDriver = Driver.webDriver
      try {
        //val screenshot             = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
        //val js: JavascriptExecutor = wd.asInstanceOf[JavascriptExecutor]
        val srcFile: File      = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.FILE)
        val screenShot: String =
          "./target/screenshots/" + Calendar.getInstance.getTime + ScreenShotMeta.scenarioName + ".png"
        FileUtils.copyFile(srcFile, new File(screenShot))
      } catch {
        case somePlatformsDontSupportScreenshots: WebDriverException =>
          System.err.printf(somePlatformsDontSupportScreenshots.getMessage + "\n")
      }
    }
}
