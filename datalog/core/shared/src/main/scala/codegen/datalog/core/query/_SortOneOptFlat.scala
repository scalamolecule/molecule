package codegen.datalog.core.query

import codegen.DatomicGenBase

object _SortOneOptFlat extends DatomicGenBase("SortOneOptFlat", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.time.*
       |import java.util.{Date, UUID}
       |import molecule.core.ast.DataModel.*
       |
       |
       |trait $fileName_[Tpl] extends ResolveBase { self: Model2DatomicQuery[Tpl] =>
       |
       |  private def compare(
       |    a: Row,
       |    b: Row,
       |    i: Int,
       |    compareMapValues: (Any, Any) => Int
       |  ): Int = {
       |    (a.get(i), b.get(i)) match {
       |      case (`none`, `none`)   => 0
       |      case (`none`, _)        => -1
       |      case (_, `none`)        => 1
       |      case (v1: Any, v2: Any) => compareMapValues(v1, v2)
       |    }
       |  }
       |$sorters
       |}""".stripMargin
  }

  def sorter(tpe: String): String = {
    val cast: String => String = tpe match {
      case "Id"             => (v: String) => s"$v.toString.toLong"
      case "Int"            => (v: String) => s"$v.toString.toInt"
      case "Duration"       => (v: String) => s"Duration.parse($v.toString)"
      case "Instant"        => (v: String) => s"Instant.parse($v.toString)"
      case "LocalDate"      => (v: String) => s"LocalDate.parse($v.toString)"
      case "LocalTime"      => (v: String) => s"LocalTime.parse($v.toString)"
      case "LocalDateTime"  => (v: String) => s"LocalDateTime.parse($v.toString)"
      case "OffsetTime"     => (v: String) => s"OffsetTime.parse($v.toString)"
      case "OffsetDateTime" => (v: String) => s"OffsetDateTime.parse($v.toString)"
      case "ZonedDateTime"  => (v: String) => s"ZonedDateTime.parse($v.toString)"
      case _                => (v: String) => s"$v.asInstanceOf[${javaTypes(tpe)}]"
    }
    s"""
       |  protected def sortOneOptFlat$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
       |    attr.sort.map { sort =>
       |      (
       |        sort.last.toString.toInt,
       |        sort.head match {
       |          case 'a' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(a, b, i, (v1, v2) =>
       |                ${cast("v1")}.compareTo(${cast("v2")}))
       |          case 'd' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(b, a, i, (v1, v2) =>
       |                ${cast("v1")}.compareTo(${cast("v2")}))
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }
}
