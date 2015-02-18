package com.github.sonenko.scalajs.samples.validatebracesinstring

import scala.scalajs.js
import org.scalajs.jquery._
import js.Dynamic.{ global => g }

/**
 * checks if str valid line for IDE(for example)
 * check("{}") => true
 * check("}{") => false
 * check("[{]}") => false
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
object SampleStringValidator {
  def init(): Unit = {
    jQuery("#validate-braces-in-string").click{onClick _}

    def onClick(): Unit ={
      val str = g.prompt("enter string to test", """var a = {"a": ["hello", "world", function(){}]}""").toString
      g.alert(checkTailRec(str))
    }
  }

  def check(str: String): Boolean = {
    val opposite: Map[Char, Char] = Map('{' -> '}', '(' -> ')', '[' -> ']')
    val enters = opposite.keys
    val exits = opposite.values
    def doCheck(str: List[Char], expected: List[Char]): Boolean = (str, expected) match {
      case (Nil, exp) => exp.isEmpty
      case (x::xs, e) if enters.exists(_ == x) => doCheck(xs, opposite(x) :: e)
      case (x::xs, Nil) if exits.exists(_ == x) => false
      case (x::xs, eHead::eTail) if exits.exists(_ == x) => x == eHead && doCheck(xs, eTail)
      case (_::xs, e) => doCheck(xs, e)
    }
    doCheck(str.toList, Nil)
  }

  def checkInLoop(str: String): Boolean = {
    val opposite: Map[Char, Char] = Map('{' -> '}', '(' -> ')', '[' -> ']')
    var expected: List[Char] = Nil
    for { x <- str} {
      (x, expected) match {
        case ('{' | '[' | '(', e) => expected = opposite(x) :: e
        case ('}' | ']' | ')', Nil) => return false
        case ('}' | ']' | ')', eHead :: eTail) => if (x != eHead) return false else expected = eTail
        case _ =>
      }
    }
    expected.isEmpty
  }

  @annotation.tailrec final def checkTailRec(x: String, ps: List[Int] = Nil, i: Int = 0): Boolean =
    if (i == x.length) ps.isEmpty else x.charAt(i) match {
      case '(' | '{' | '[' => checkTailRec(x, i :: ps, i + 1)
      case ')' => if (!ps.isEmpty && x.charAt(ps.head) == '(') checkTailRec(x, ps.tail, i + 1) else false
      case ']' => if (!ps.isEmpty && x.charAt(ps.head) == '[') checkTailRec(x, ps.tail, i + 1) else false
      case '}' => if (!ps.isEmpty && x.charAt(ps.head) == '{') checkTailRec(x, ps.tail, i + 1) else false
      case _ => checkTailRec(x, ps, i + 1)
    }
}