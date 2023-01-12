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

import java.time.Duration

case class Configuration(authLoginUrl: String, applicationsBaseUrl: String, manageBaseUrl: String, timeout: Int) {
  val duration: Duration = Duration.ofSeconds(timeout)
}

object Configuration {

  private val traderArrivalUri                 = "common-transit-convention-trader-arrival"
  private val unloadingRemarkPort              = 9488
  private val unloadingArrivalUri              = "manage-transit-movements-unloading-remarks"
  private val manageTraderTransitMovementsPort = 9485
  private val manageTraderTransitMovementsUri  = "manage-transit-movements"
  private val authLoginPort                    = 9949

  val environment: Environment.Name = {
    val env = Environment.apply(System.getProperty("environment", "local"))
    println(s"Running in environment $env")
    env
  }

  def apply(): Configuration = environment match {
    case Environment.dev     =>
      new Configuration(
        authLoginUrl = "https://www.development.tax.service.gov.uk/auth-login-stub/gg-sign-in",
        applicationsBaseUrl = s"https://www.development.tax.service.gov.uk/$unloadingArrivalUri",
        manageBaseUrl = s"https://www.development.tax.service.gov.uk/$manageTraderTransitMovementsUri",
        timeout = 30
      )
    case Environment.local   =>
      new Configuration(
        authLoginUrl = s"http://localhost:$authLoginPort/auth-login-stub/gg-sign-in",
        applicationsBaseUrl = s"http://localhost:$unloadingRemarkPort/$unloadingArrivalUri",
        manageBaseUrl = s"http://localhost:$manageTraderTransitMovementsPort/$manageTraderTransitMovementsUri",
        timeout = 30
      )
    case Environment.qa      =>
      new Configuration(
        authLoginUrl = "https://www.qa.tax.service.gov.uk/auth-login-stub/gg-sign-in",
        applicationsBaseUrl = s"https://www.qa.tax.service.gov.uk/$unloadingArrivalUri",
        manageBaseUrl = s"https://www.qa.tax.service.gov.uk/$manageTraderTransitMovementsUri",
        timeout = 30
      )
    case Environment.staging =>
      new Configuration(
        authLoginUrl = "https://www.staging.tax.service.gov.uk/auth-login-stub/gg-sign-in",
        applicationsBaseUrl = s"https://www.staging.tax.service.gov.uk/$traderArrivalUri",
        manageBaseUrl = s"https://www.staging.tax.service.gov.uk/$manageTraderTransitMovementsUri",
        timeout = 30
      )
    case _                   => throw new IllegalArgumentException(s"Environment '$environment' not known")
  }

  object Environment extends Enumeration {
    type Name = Value
    val local, dev, qa, staging = Value

    def apply(value: String): Name = value match {
      case "local"   => Environment.local
      case "dev"     => Environment.dev
      case "qa"      => Environment.qa
      case "staging" => Environment.staging
      case _         => throw new IllegalArgumentException(s"Environment '$value' not known")
    }
  }
}
