package com.github.sonenko.scalajs.samples

import utest._
import utest.framework.{TestSuite, Test}
import utest.util.Tree
import scala.util.Try


object SimpleNumberF {
  def test(name: String, function: Int => Vector[Int]): Tree[Test] = {

    TestSuite {
      s"$name" - {
        "should return empty vector if argument is 0" - {
          assert(
            function(0) == Vector[Int]()
          )
        }
        "Throw exception if negative argument provided" - {
          assert(
            Try(function(-1)).isFailure
          )
        }
        "return Vector(1) if argument is 1" - {
          assert(
            function(1) == Vector(1)
          )
        }
        "return Vector(1, 2, 3, 5, 7, 11, 13, 17, 19, 23) if argument is 10" - {
          assert(
            function(10) == Vector(1, 2, 3, 5, 7, 11, 13, 17, 19, 23)
          )
        }
      }
    }
  }
}

object SimpleNumbersStreamTest extends TestSuite {
  val tests = SimpleNumberF.test("simpleNumbersStream", SimpleNumber.simpleNumbersStream)
}

object SimpleNumbersTailRecTest extends TestSuite {
  val tests = SimpleNumberF.test("simpleNumbersTailRec", SimpleNumber.simpleNumbersTailRec)
}