import sbt._, Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Build extends Build {

  val commonSettings = Seq(
    organization := "com.github.sonenko.scalajs.samples",
    description := "Scala-js samples",
    scalaVersion := "2.11.5",
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature", "-Xlog-reflective-calls", "-Xfuture", "-Xlint"),
    scalaJSStage in Global := FastOptStage,
    persistLauncher in Compile := true,
    persistLauncher in Test := false,
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

  lazy val proj = project
    .in(file("."))
    .aggregate(root)
    .settings(commonSettings:_*)

  lazy val root = project
    .settings(libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.8.0",
      "be.doeraene" %%% s"scalajs-jquery" % "0.8.0",
      "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
    ))
    .settings(commonSettings:_*)
    .enablePlugins(ScalaJSPlugin)
}

