name := "testing-scala"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++=  Seq(
  "org.scalatest" %% "scalatest" % "3.0.0-RC3" % "test" withSources() withJavadoc(),
  "org.scalactic" %% "scalactic" % "3.0.0-RC3" % "test" withSources() withJavadoc(),
  "joda-time" % "joda-time" % "1.6.2" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.13.0" % "test" withSources() withJavadoc()
)