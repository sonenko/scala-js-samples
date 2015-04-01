package com.github.sonenko.scalajs.samples

import org.scalajs.jquery._
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}

object SampleSort {

  def init(): Unit = {
    def onClick(): Unit = {
      val str = g.prompt("inport string to sort", """3201""").toString
      g.alert(sortBobbleImperative(str.toList).mkString)
    }

    val el = jQuery("<div><button>Bubble sort</button></div>").click(onClick _)
    jQuery("body").append(el)
  }

  /**
   * JS realization: https://en.wikibooks.org/wiki/Algorithm_Implementation/Sorting/Bubble_sort
     function sortBubble(data) {
        var tmp;

        for (var i = data.length - 1; i > 0; i--) {
            for (var j = 0; j < i; j++) {
                if (data[j] > data[j+1]) {
                    tmp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = tmp;
                }
            }
        }
        return data;
     }
   */
  def sortBobbleImperative(input: List[Char]): List[Char] = {
    val data = input.toArray
    for (i <- 0 until data.length - 1; j <- 0 until data.length - 1 - i) {
      if (data(j) > data(j + 1)) {
        val temp = data(j)
        data.update(j, data(j + 1))
        data.update(j + 1, temp)
      }
    }
    data.toList
  }
}




























