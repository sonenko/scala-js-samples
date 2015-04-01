package com.github.sonenko.scalajs.samples

import scala.annotation.tailrec

object SimpleNumber {

  /**
   * pure JS realization example
  function simpleNumbers(count) {
    var acc = [],
        i = 1;
    for (; acc.length < count; i++) {
      isNextSimple(i) && acc.push(i);
      if (i > 100) return acc;
    }

    function isNextSimple(check) {
      var i = 0,
          value = null;
      for (; i < acc.length; i++) {
        value = acc[i];
        if (check % value === 0 && value !== 1) return false;
      }
      return true;
    }

    return acc;
  }
   */

  def simpleNumbersStream(count: Int): Vector[Int] = {
    require(count >=0, "can generate vector of negative length")
    def stream(x: Int, acc: List[Int]): Stream[Int] =
      if (acc.exists(y => x % y == 0 && y != 1)) stream(x + 1, acc)
      else x #:: stream(x + 1, x :: acc)
    stream(1, Nil).take(count).toVector
  }


  def simpleNumbersTailRec(count: Int): Vector[Int] = {
    require(count >=0, "can generate vector of negative length")
    @tailrec
    def rec(x: Int, acc: Vector[Int]): Vector[Int] =
      if (acc.length >= count) acc
      else if (acc.exists(y => x % y == 0 && y != 1)) rec(x + 1, acc)
      else rec(x + 1, acc :+ x)
    rec(1, Vector[Int]())
  }

}
