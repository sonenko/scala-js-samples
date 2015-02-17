package com.github.sonenko.scalajs.samples

import scala.scalajs.js.JSApp

import com.github.sonenko.scalajs.samples.validatebracesinstring.SampleStringValidator

object Main extends JSApp {
  def main(): Unit = ()

  SampleStringValidator.init()
}