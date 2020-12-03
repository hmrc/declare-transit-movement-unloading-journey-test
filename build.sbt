name := "declare-transit-movement-unloading-journey-test"

version := "1.0"

scalaVersion := "2.12.12"

val hmrcRepoHost = java.lang.System.getProperty("hmrc.repo.host", "https://nexus-preview.tax.service.gov.uk")

resolvers ++= Seq(
  "hmrc-snapshots" at hmrcRepoHost + "/content/repositories/hmrc-snapshots",
  "hmrc-releases" at hmrcRepoHost + "/content/repositories/hmrc-releases",
  "typesafe-releases" at hmrcRepoHost + "/content/repositories/typesafe-releases"
)

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "HMRC Bintray" at "https://dl.bintray.com/hmrc/releases"

resolvers += "HMRC Bintray RCs" at "https://dl.bintray.com/hmrc/release-candidates"

val seleniumVersion = "3.141.59"
val cucumberVersion = "2.0.1"

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium"    % "selenium-java"                   % seleniumVersion,
  "org.seleniumhq.selenium"    % "selenium-firefox-driver"         % seleniumVersion,
  "commons-io"                 % "commons-io"                      % "2.5",
  "com.novocode"               % "junit-interface"                 % "0.11" % "test",
  "io.cucumber"                % "cucumber-scala_2.12"             % cucumberVersion % "test",
  "io.cucumber"                % "cucumber-picocontainer"          % cucumberVersion % "test",
  "io.cucumber"                % "cucumber-junit"                  % cucumberVersion % "test",
  "junit"                      % "junit"                           % "4.13" % "test",
  "uk.gov.hmrc"                %% "play-language"                  % "4.3.0-play-27",
  "org.mongodb.scala"          %% "mongo-scala-driver"             % "4.0.4",
  "org.pegdown"                % "pegdown"                         % "1.6.0" % "test",
  "org.scalatest"              %% "scalatest"                      % "3.0.8",
  "ch.qos.logback"             % "logback-classic"                 % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging"                  % "3.8.0",
  "uk.gov.hmrc"                %% "zap-automation"                 % "2.7.0",
  "uk.gov.hmrc"                %% "webdriver-factory"              % "0.15.0",
  "uk.gov.hmrc"                %% "extent-report"                  % "0.8.0",
  "com.aventstack"             % "extentreports"                   % "4.0.9" % "provided",
  "com.aventstack"             % "extentreports-cucumber2-adapter" % "1.0.0" % "provided"
)

scalafmtOnCompile in ThisBuild := true
