import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.github"
ThisBuild / organizationName := "mioalter"

lazy val root = (project in file("."))
  .settings(
    name := "tigris",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.1" % "test",
    libraryDependencies += "org.typelevel" %% "discipline-core" % "1.0.0"
  )

scalacOptions ++= Seq("-deprecation", "-feature")
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
