name := "AirBNB"

version := "0.1"

scalaVersion := "2.12.10"

organization := "com.phasmidsoftware"

scalacOptions += "-deprecation"

Test / unmanagedSourceDirectories += baseDirectory.value / "src/it/scala"
Test / unmanagedResourceDirectories += baseDirectory.value / "src/it/resources"

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

lazy val scalaModules = "org.scala-lang.modules"
lazy val scalaTestVersion = "3.2.9"
lazy val scalaParserCombinatorsVersion = "1.1.2"
lazy val nScalaTimeVersion = "2.28.0"

libraryDependencies ++= Seq(
  "com.phasmidsoftware" %% "tableparser" % "1.0.14",
  "org.apache.spark" %% "spark-core" % "3.1.2",
  "org.apache.spark" %% "spark-sql" % "3.1.2",
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)

