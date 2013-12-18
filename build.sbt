name := "generator"

version := "0.1.0"

scalaVersion := "2.10.3"

addCompilerPlugin("org.scala-lang.plugins" % "continuations" % "2.10.3")

scalacOptions += "-P:continuations:enable"

libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.10" % "2.0" % "test")
