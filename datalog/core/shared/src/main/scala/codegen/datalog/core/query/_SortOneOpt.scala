package codegen.datalog.core.query

import codegen.DatomicGenBase

object _SortOneOpt extends DatomicGenBase("SortOneOpt", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.time._
       |import java.util.{Date, UUID, Map => jMap}
       |import molecule.core.ast.DataModel._
       |
       |
       |trait $fileName_[Tpl] { self: Model2DatomicQuery[Tpl] =>
       |
       |  private def compare(
       |    a: Row,
       |    b: Row,
       |    i: Int,
       |    compareMapValues: (jMap[?, ?], jMap[?, ?]) => Int
       |  ): Int = {
       |    (a.get(i), b.get(i)) match {
       |      case (null, null)                     => 0
       |      case (null, _)                        => -1
       |      case (_, null)                        => 1
       |      case (m1: jMap[_, _], m2: jMap[_, _]) => compareMapValues(m1, m2)
       |    }
       |  }
       |$sorters
       |}""".stripMargin
  }

  def sorter(tpe: String): String = {
    val cast: String => String = tpe match {
      case "ID"             => (map: String) => s"$map.values.iterator.next.asInstanceOf[jMap[?, ?]].values.iterator.next.toString.toLong"
      case "Int"            => (map: String) => s"$map.values.iterator.next.toString.toInt"
      case "Duration"       => (map: String) => s"Duration.parse($map.values.iterator.next.toString)"
      case "Instant"        => (map: String) => s"Instant.parse($map.values.iterator.next.toString)"
      case "LocalDate"      => (map: String) => s"LocalDate.parse($map.values.iterator.next.toString)"
      case "LocalTime"      => (map: String) => s"LocalTime.parse($map.values.iterator.next.toString)"
      case "LocalDateTime"  => (map: String) => s"LocalDateTime.parse($map.values.iterator.next.toString)"
      case "OffsetTime"     => (map: String) => s"OffsetTime.parse($map.values.iterator.next.toString)"
      case "OffsetDateTime" => (map: String) => s"OffsetDateTime.parse($map.values.iterator.next.toString)"
      case "ZonedDateTime"  => (map: String) => s"ZonedDateTime.parse($map.values.iterator.next.toString)"
      case _                => (map: String) => s"$map.values.iterator.next.asInstanceOf[${javaTypes(tpe)}]"
    }
    s"""
       |  protected def sortOneOpt$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
       |    attr.sort.map { sort =>
       |      (
       |        sort.last.toString.toInt,
       |        sort.head match {
       |          case 'a' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(a, b, i, (m1: jMap[?, ?], m2: jMap[?, ?]) =>
       |                ${cast("m1")}.compareTo(
       |                  ${cast("m2")})
       |              )
       |          case 'd' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(b, a, i, (m1: jMap[?, ?], m2: jMap[?, ?]) =>
       |                ${cast("m1")}.compareTo(
       |                  ${cast("m2")})
       |              )
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }
}
