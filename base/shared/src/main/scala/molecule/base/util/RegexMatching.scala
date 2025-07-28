package molecule.base.util

import scala.annotation.nowarn

trait RegexMatching {

  implicit class Regex(sc: StringContext) {
    // Allow for sbt-molecule use on scala 2.12
    @nowarn
    def r = new scala.util.matching.Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)

    // For scala 3.7 it would be
    //    def r = new scala.util.matching.Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x")*)
  }
}
