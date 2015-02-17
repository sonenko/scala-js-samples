package project

import sbt._
import sbt.Keys._

object ProjectBuild extends Build {
  lazy val subsBuild = project.in(file(".")).settings(List(
    addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0"),
    addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.0")
  ): _*)
}
