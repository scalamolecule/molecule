package codegen.datalog.core.query

import codegen.DatomicGenBase

object _SortOne extends DatomicGenBase("SortOne", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.time._
       |import java.util.{Date, UUID}
       |import molecule.core.ast.DataModel._
       |
       |
       |trait $fileName_[Tpl] { self: Model2DatomicQuery[Tpl] =>
       |$sorters
       |}""".stripMargin
  }

  def sorter(tpe: String): String = {
    val cast: String => String = tpe match {
      case "ID"             => (row: String) => s"$row.get(i).asInstanceOf[jLong]"
      case "Int"            => (row: String) => s"$row.get(i).toString.toInt"
      case "Duration"       => (row: String) => s"Duration.parse($row.get(i).toString)"
      case "Instant"        => (row: String) => s"Instant.parse($row.get(i).toString)"
      case "LocalDate"      => (row: String) => s"LocalDate.parse($row.get(i).toString)"
      case "LocalTime"      => (row: String) => s"LocalTime.parse($row.get(i).toString)"
      case "LocalDateTime"  => (row: String) => s"LocalDateTime.parse($row.get(i).toString)"
      case "OffsetTime"     => (row: String) => s"OffsetTime.parse($row.get(i).toString)"
      case "OffsetDateTime" => (row: String) => s"OffsetDateTime.parse($row.get(i).toString)"
      case "ZonedDateTime"  => (row: String) => s"ZonedDateTime.parse($row.get(i).toString)"
      case _                => (row: String) => s"$row.get(i).asInstanceOf[${javaTypes(tpe)}]"
    }
    s"""
       |  protected def sortOne$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
       |    attr.sort.map { sort =>
       |      (
       |        sort.last.toString.toInt,
       |        sort.head match {
       |          case 'a' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              ${cast("a")}.compareTo(${cast("b")})
       |          case 'd' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              ${cast("b")}.compareTo(${cast("a")})
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }
}
