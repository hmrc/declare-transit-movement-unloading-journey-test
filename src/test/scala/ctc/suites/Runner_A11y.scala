package ctc.suites

import java.io.File

import ctc.driver.Driver
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import uk.gov.hmrc.extentreport.ExtentProperties
import uk.gov.hmrc.extentreport.Reporter

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("src/test/resources/features"),
  glue = Array("ctc.steps"),
  plugin = Array("pretty", "html:target/cucumber.html", "json:target/cucumber.json"),
  tags = "@unloading_remarks or @legacyenrolment"
)
class Runner_A11y

object Runner_A11y extends Runner_A11y {

  @BeforeClass
  def setup(): Unit = {
    val dirName          = "target/test-reports/html-report"
    val extentProperties = ExtentProperties.INSTANCE
    val dir              = new File(dirName)
    val successful       = dir.mkdir()
    ExtentProperties.create(Driver.webDriver, dirName + "/index.html")
  }

  @AfterClass
  def writeExtentReport(): Unit =
    Reporter.loadXMLConfig("src/test/resources/extent-config.xml")
}
