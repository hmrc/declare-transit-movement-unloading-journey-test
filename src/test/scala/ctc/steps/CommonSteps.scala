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

package ctc.steps

import ctc.pages._
import ctc.utils.ScreenShotUtility
import io.cucumber.scala.EN
import io.cucumber.scala.ScalaDsl

class CommonSteps extends ScalaDsl with EN with ScreenShotUtility {

  And("""^(?:I )?pause execution for (.+) milliseconds$""") { delay: Int =>
    Thread.sleep(delay)
  }

  Given("""^I clear my cookies""") {
    Page.clearCookies()
  }

  Given("""^I am on the Unloading remarks start page for Arrival Id (.*)$""") { (arrivalId: String) =>
    Page.goToUnloadingRemarksHomePage(arrivalId)
  }

  Given("""^I authenticate on (.+) page for Arrival Id (.*)$""") { (prettyUrl: String, arrivalId: String) =>
    Page.goToUnloadingRemarksRejectionPage(prettyUrl, arrivalId)
  }

  Given("""^I authenticate on (.+) page with an enrolment as (.+)$""") { (prettyUrl: String, enrolmentType: String) =>
    Page.authenticateEnrolment(enrolmentType)
  }

  And("""^(?:I )?enter (.+) on the (.+) page$""") { (answer: String, url: String) =>
    Page.clear()
    Page.accessibilityCheck()
    Page.submitValuePage(url, answer)
  }

  When("""^(?:I )?answer ([Yy]es|[Nn]o) on the (.+) page$""") { (answer: String, prettyUrl: String) =>
    Page.accessibilityCheck()
    Page.submitYesNoPage(prettyUrl, answer)
  }

  When("""^(?:I )?input today's date on the (.+) page$""") { (prettyUrl: String) =>
    Page.accessibilityCheck()
    Page.submitDateNowPage(prettyUrl)
  }

  Then("""^(?:I )?(?:should )?be on the (.+) page$""") { prettyUrl: String =>
    Page.urlShouldMatch(prettyUrl)
  }

//  When I verify the url contains customs-enrolment-services/ctc/subscribe

  When("""^I verify the url contains (.+)""") { prettyUrl: String =>
    Page.urlShouldContain(prettyUrl)
  }

  And("""^(?:I )?clicked the (.+) button$""") { submit: String =>
    Page.clickById(submit)
  }

  When("""^(?:I )?click the (.+) link$""") { linkText: String =>
    Page.clickByLinkText(linkText)
  }

  And("""^(?:I )?click on change (.+)""") { page: String =>
    val id       = page.replace(" ", "-").toLowerCase
    val actualID = s"change-$id"
    Page.clickById(actualID)
  }

  When("""^(?:I )?click on (.+) for item (.+)""") { (action: String, index: Int) =>
    val updateIndex = index - 1
    val id          = s"${action.replace(" ", "-")}-$updateIndex"
    Page.clickById(id)
  }

}
