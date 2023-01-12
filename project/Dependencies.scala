import sbt._

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"         %% "webdriver-factory"  % "0.41.0",
    "org.scalatest"       %% "scalatest"          % "3.2.15",
    "io.cucumber"         %% "cucumber-scala"     % "8.13.1",
    "io.cucumber"          % "cucumber-junit"     % "7.10.1",
    "junit"                % "junit"              % "4.13.2",
    "com.novocode"         % "junit-interface"    % "0.11",
    "com.typesafe"         % "config"             % "1.4.2",
    "commons-io"           % "commons-io"         % "2.11.0",
    "com.vladsch.flexmark" % "flexmark-all"       % "0.62.2"
  ).map(_ % Test)

}
