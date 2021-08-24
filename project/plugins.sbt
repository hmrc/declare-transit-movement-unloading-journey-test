
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"

resolvers += Resolver.url("HMRC-open-artefacts-ivy", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.16")
