resolvers ++= Seq(
  Resolver.bintrayIvyRepo("typesafe", "sbt-plugins"),
  "Tatami Releases" at "https://raw.github.com/cchantep/tatami/master/releases"
)

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.5")

addSbtPlugin("cchantep" % "sbt-scaladoc-compiler" % "0.3")

addSbtPlugin("cchantep" % "sbt-hl-compiler" % "0.8")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.3.1")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.10.0-RC1")

addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.1.4")

addSbtPlugin("com.github.sbt" % "sbt-release" % "1.4.0")

addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.4.7")

addSbtPlugin("com.github.sbt" % "sbt-dynver" % "5.1.1")

addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.14.3")

addSbtPlugin("cchantep" % "sbt-velocity" % "0.1")
