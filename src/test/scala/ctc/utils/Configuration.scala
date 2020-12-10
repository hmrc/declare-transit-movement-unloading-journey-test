package ctc.utils

import java.time.LocalDate

import scala.util.Try

case class Configuration(authLoginUrl: String, applicationsBaseUrl: String, manageBaseUrl: String, timeout: Int)

object Configuration {

  val traderArrivalPort                = 9483
  val traderArrivalUri                 = "common-transit-convention-trader-arrival"
  val unloadingRemarkPort              = 9488
  val unloadingArrivalUri              = "manage-transit-movements-unloading-remarks"
  val manageTraderTransitMovementsPort = 9485
  val manageTraderTransitMovementsUri  = "manage-transit-movements"
  val authLoginPort                    = 9949

  val local_date: LocalDate = Try(LocalDate.parse(System.getProperty("local-date", ""))).getOrElse(LocalDate.now)

  val environment: Environment.Name = {
    val environmentProperty = System.getProperty("environment", "local")

    val env = environmentProperty match {
      case "local"   => Environment.local
      case "dev"     => Environment.dev
      case "qa"      => Environment.qa
      case "staging" => Environment.staging
      case _         => throw new IllegalArgumentException(s"Environment '$environmentProperty' not known")
    }

    println(s"Running in environment $env")

    env
  }

  lazy val settings: Configuration = create()

  def create(): Configuration =
    environment match {
      case Environment.dev =>
        new Configuration(
          authLoginUrl = "https://www.development.tax.service.gov.uk/auth-login-stub/gg-sign-in",
          applicationsBaseUrl = s"https://www.development.tax.service.gov.uk/$unloadingArrivalUri",
          manageBaseUrl = s"https://www.development.tax.service.gov.uk/$manageTraderTransitMovementsUri",
          timeout = 30
        )
      case Environment.local =>
        new Configuration(
          authLoginUrl = s"http://localhost:$authLoginPort/auth-login-stub/gg-sign-in",
          applicationsBaseUrl = s"http://localhost:$unloadingRemarkPort/$unloadingArrivalUri",
          manageBaseUrl = s"http://localhost:$manageTraderTransitMovementsPort/$manageTraderTransitMovementsUri",
          timeout = 30
        )
      case Environment.qa =>
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
      case _ => throw new IllegalArgumentException(s"Environment '$environment' not known")
    }

  object Environment extends Enumeration {
    type Name = Value
    val local, dev, qa, staging = Value
  }
}
