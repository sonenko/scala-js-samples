package com.github.sonenko.scalajs.samples

import scala.scalajs.js.JSApp

object Main extends JSApp {
  def main(): Unit = {
    SampleStringValidator.init()
    SampleSort.init()
  }
}