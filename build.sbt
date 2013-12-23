name := "generator"

organization := "org.jcrudy"

version := "0.1.0"

scalaVersion := "2.10.3"

addCompilerPlugin("org.scala-lang.plugins" % "continuations" % "2.10.3")

scalacOptions += "-P:continuations:enable"

libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.10" % "2.0" % "test")

publishArtifact in Test := false

licenses := Seq("BSD-2-clause" -> url("file://LICENSE"))

homepage := Some(url("https://github.com/jcrudy/generator"))

pomExtra := (
  <scm>
    <url>git@github.com:jcrudy/generator.git</url>
    <connection>scm:git:git@github.com:jcrudy/scala-arm.git</connection>
  </scm>
  <developers>
    <developer>
      <id>jcrudy</id>
      <name>Jason Rudy</name>
      <url>http://jcrudy.github.io</url>
    </developer>
  </developers>)
