package ctc.utils

import java.io.File
import java.util.Calendar

import cucumber.api.Scenario
import cucumber.api.java.Before
import ctc.driver.Driver
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium._
import org.apache.commons.io.FileUtils
import org.openqa.selenium.JavascriptExecutor

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
        val screenshot             = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
        val js: JavascriptExecutor = wd.asInstanceOf[JavascriptExecutor]
        val srcFile: File          = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.FILE)
        val screenShot: String     = "./target/screenshots/" + Calendar.getInstance.getTime + ScreenShotMeta.scenarioName + ".png"
        FileUtils.copyFile(srcFile, new File(screenShot))
      } catch {
        case somePlatformsDontSupportScreenshots: WebDriverException =>
          System.err.printf(somePlatformsDontSupportScreenshots.getMessage + "\n")
      }
    }
}
