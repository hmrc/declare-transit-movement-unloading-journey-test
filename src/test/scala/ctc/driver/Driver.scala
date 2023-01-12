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

package ctc.driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import uk.gov.hmrc.webdriver.SingletonDriver

import scala.util.Try

object Driver extends Driver

class Driver {

  val options = new ChromeOptions()
  options.setHeadless(true)

  val webDriver: WebDriver = sys.props.get("browsermode") match {
    case Some("headless") => SingletonDriver.getInstance(Some(options))
    case _                => SingletonDriver.getInstance()
  }

  sys addShutdownHook {
    Try(SingletonDriver.closeInstance())
  }
}
