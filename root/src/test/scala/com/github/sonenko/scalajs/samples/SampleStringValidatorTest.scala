package com.github.sonenko.scalajs.samples

import utest._
import utest.framework.Test
import utest.util.Tree

object SampleStringValidatorF {
  def testSimilar(name: String, func: String => Boolean): Tree[Test] = TestSuite {
    s"$name" - {
      "return `true` for empty input" - {
        assert(SampleStringValidator.check(""))
      }
      "return `true` for valid inputs" - {
        assert(
          func("{}"),
          func("[]"),
          func("()"),
          func("[({})]")
        )
      }
      "ignore other symbols" - {
        assert(func( """{"hello": "world"}"""))
      }
      "return `false` for invalid strings" - {
        assert(!func("}"),
          !func("{"),
          !func("{(})")
        )
      }
    }
  }
}

object CheckTest extends TestSuite {
  val tests = SampleStringValidatorF.testSimilar("check", SampleStringValidator.check)
}

object CheckInLoopTest extends TestSuite {
  val tests = SampleStringValidatorF.testSimilar("checkInLoop", SampleStringValidator.checkInLoop)
}

object CheckTailrecTest extends TestSuite {
  val tests = SampleStringValidatorF.testSimilar("checkTailRec", SampleStringValidator.checkTailRec(_))
}