package ctc.suites

import java.io.File

import ctc.driver.Driver
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import uk.gov.hmrc.extentreport.ExtentProperties
import uk.gov.hmrc.extentreport.Reporter

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("src/test/resources/features"),
  glue = Array("ctc.steps"),
  format = Array(
    "pretty",
    "html:target/cucumber",
    "json:target/cucumber.json",
    "uk.gov.hmrc.extentreport.ExtentCucumberFormatter:target/test-reports/html-report/index.html"
  ),
  tags = Array("@unloading_remarks")
)
class Runner

object Runner extends Runner {

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
