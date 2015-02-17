package com.github.sonenko.scalajs.samples.validatebracesinstring

import scala.scalajs.js
import org.scalajs.jquery._
import js.Dynamic.{ global => g }

object SampleStringValidator {
  def init(): Unit = {
    jQuery("#validate-braces-in-string").click{onClick _}

    def onClick(): Unit ={
      val str = g.prompt("enter string to test", """var a = {"a": ["hello", "world", function(){}]}""").toString
      g.alert(check(str))
    }
  }

  /**
   * checks if str valid line for IDE(for example)
   * "{}" => true
   * "}{" => false
   * "[{]}" => false
   */
  def check(str: String): Boolean = {
    val opposite: Map[Char, Char] = Map('{' -> '}', '(' -> ')', '[' -> ']')
    def doCheck(str: List[Char], expected: List[Char]): Boolean = (str, expected) match {
      case (Nil, exp) => exp.length == 0
      case ((x@('{' | '[' | '(')) :: xs, e) => doCheck(xs, opposite(x) :: e)
      case (('}' | ']' | ')') :: xs, Nil) => false
      case ((x@('}' | ']' | ')')) :: xs, eHead :: eTail) => x == eHead && doCheck(xs, eTail)
      case (_ :: xs, e) => doCheck(xs.toList, e)
    }
    doCheck(str.toList, Nil)
  }

  /**
   * --- pure JS realization example
   * function check(str) {
   *   var enters = ['{', '(', '['],
   *     exits = ['}', ')', ']'],
   *     opposite = (function(){
   *         var res = {}, i = 0;
   *         for (; i < enters.length; i++) { res[enters[i]] = exits[i]; }
   *         return res;
   *       }()),
   *     expected = [],
   *     key = null,
   *     char = null;
   *
   *   for (key in str) {
   *     char = str[key];
   *     if (enters.indexOf(char) >= 0) {
   *       expected.unshift(opposite[char]);
   *     } else if (exits.indexOf(char) >= 0) {
   *       if (expected.length === 0 || char != expected[0]) return false;
   *       else expected.shift();
   *     }
   *   }
   *
   *   return true;
   * }
   */
  def checkInLoop(str: String): Boolean = {
    val opposite: Map[Char, Char] = Map('{' -> '}', '(' -> ')', '[' -> ']')
    var expected: List[Char] = Nil
    for { x <- str} {
      (x, expected) match {
        case ('{' | '[' | '(', e) => expected = opposite(x) :: expected
        case ('}' | ']' | ')', Nil) => return false
        case ('}' | ']' | ')', eHead :: eTail) if x != eHead => return false
        case ('}' | ']' | ')', eHead :: eTail) => expected = eTail
        case _ =>
      }
    }
    true
  }
}