package com.github.sonenko.scalajs.samples

import utest._
import utest.framework.Test
import utest.util.Tree

import scala.reflect.ClassTag
import scala.util.Try

object SampleSortF {
  def testSimilar(name: String, function: List[Char] => List[Char]): Tree[Test] = {
    def func(x: String): String = function(x.toList).mkString

    TestSuite {
      s"$name" - {
        "result should be same length as input" - {
          val arg1 = ""
          val arg2 = "1"
          val arg3 = "12"

          assert(
            func(arg1).length == arg1.length,
            func(arg2).length == arg2.length,
            func(arg3).length == arg3.length
          )
        }
        "Throw exception if null argument" - {
          assert(
            Try(func(null)).isFailure
          )
        }
        "sort correctly" - {
          assert(
            func("321") == "123",
            func("94851") == "14589",
            func("99337711") == "11337799"
          )
        }
      }
    }
  }
}

object SampleSortImperativeTest extends TestSuite {
  val tests = SampleSortF.testSimilar("sortBobbleImperative", SampleSort.sortBobbleImperative)
}

