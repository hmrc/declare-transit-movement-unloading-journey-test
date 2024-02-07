lazy val testSuite = (project in file("."))
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    name := "declare-transit-movement-unloading-journey-test",
    version := "1.1",
    scalaVersion := "2.13.12",
    scalacOptions ++= Seq("-feature"),
    libraryDependencies ++= Dependencies.test,
    //The testOptions from SbtAutoBuildPlugin supports only ScalaTest. Resetting testOptions for Cucumber Tests.
    Test / testOptions := Seq.empty
  )

