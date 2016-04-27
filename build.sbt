name := """akka-recover-example"""

version := "1.0"

scalaVersion := "2.11.6"

lazy val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.4.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7"

)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

fork in run := true