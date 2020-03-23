name := "declare-transit-movement-arrival-journey-tests"

version := "1.0"

scalaVersion := "2.11.11"

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
val cucumberVersion = "1.2.5"

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium"    % "selenium-java"           % seleniumVersion,
  "org.seleniumhq.selenium"    % "selenium-firefox-driver" % seleniumVersion,
  "commons-io"                 % "commons-io"              % "2.5",
  "com.novocode"               % "junit-interface"         % "0.11" % "test",
  "info.cukes"                 % "cucumber-scala_2.11"     % cucumberVersion,
  "info.cukes"                 % "cucumber-picocontainer"  % cucumberVersion,
  "info.cukes"                 % "cucumber-junit"          % cucumberVersion,
  "junit"                      % "junit"                   % "4.12" % "test",
  "uk.gov.hmrc"                %% "play-language"          % "3.4.0",
  "org.mongodb"                % "mongo-java-driver"       % "2.12.3",
  "org.pegdown"                % "pegdown"                 % "1.6.0" % "test",
  "org.scalatest"              %% "scalatest"              % "3.0.5",
  "ch.qos.logback"             % "logback-classic"         % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging"          % "3.8.0",
  "uk.gov.hmrc"                %% "zap-automation"         % "1.4.0",
  "uk.gov.hmrc"                %% "webdriver-factory"      % "0.7.0",
  "uk.gov.hmrc"                %% "extent-report"          % "0.8.0",
  "com.aventstack"             % "extentreports"           % "4.0.9" % "provided"
)

scalafmtOnCompile in ThisBuild := true
