ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.3"

val scalaTestVersion = "3.2.19"

lazy val root = (project in file("."))
  .settings(
    name := "scala-metaprogramming",
    scalacOptions ++= Seq(
      "-experimental", // Enable experimental features
      //"-Xprint:postInlining", // Print AST after inlining
    ),
    libraryDependencies += "org.scala-lang" %% "scala3-staging" % scalaVersion.value, // MSP
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % scalaTestVersion % "test",
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    )
  )
