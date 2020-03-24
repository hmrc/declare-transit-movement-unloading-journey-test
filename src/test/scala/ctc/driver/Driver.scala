package ctc.driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import uk.gov.hmrc.webdriver.SingletonDriver

object Driver extends Driver

class Driver {

  val options = new ChromeOptions()
  options.setHeadless(true)

  val webDriver: WebDriver = sys.props.get("browsermode") match {
    case Some("headless") => SingletonDriver.getInstance(Some(options))
    case _                => SingletonDriver.getInstance()
  }

  sys addShutdownHook {
    SingletonDriver.closeInstance()
  }
}
