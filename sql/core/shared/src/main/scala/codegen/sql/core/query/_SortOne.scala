package codegen.sql.core.query

import codegen.SqlGenBase

object _SortOne extends SqlGenBase("SortOne", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.boilerplate.ast.Model._
       |
       |
       |trait $fileName_[Tpl] { self: DatomicModel2Query[Tpl] =>
       |$sorters
       |}""".stripMargin
  }

  def sorter(tpe: String): String = {
    val cast = if(tpe == "Int") "toString.toInt" else s"asInstanceOf[${javaTypes(tpe)}]"
    s"""
       |  protected def sortOne$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
       |    attr.sort.map { sort =>
       |      (
       |        sort.last.toString.toInt,
       |        sort.head match {
       |          case 'a' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              a.get(i).$cast.compareTo(b.get(i).$cast)
       |          case 'd' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              b.get(i).$cast.compareTo(a.get(i).$cast)
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }
}
