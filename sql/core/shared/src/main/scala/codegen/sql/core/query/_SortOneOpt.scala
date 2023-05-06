package codegen.sql.core.query

import codegen.SqlGenBase

object _SortOneOpt extends SqlGenBase("SortOneOpt", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.util.{Date, UUID, Map => jMap}
       |import molecule.boilerplate.ast.Model._
       |
       |
       |trait $fileName_[Tpl] { self: DatomicModel2Query[Tpl] =>
       |
       |  private def compare(
       |    a: Row,
       |    b: Row,
       |    i: Int,
       |    compareMapValues: (jMap[_, _], jMap[_, _]) => Int
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
    val cast = if(tpe == "Int") "toString.toInt" else s"asInstanceOf[${javaTypes(tpe)}]"
    s"""
       |  protected def sortOneOpt$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
       |    attr.sort.map { sort =>
       |      (
       |        sort.last.toString.toInt,
       |        sort.head match {
       |          case 'a' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
       |                m1.values.iterator.next.$cast.compareTo(
       |                  m2.values.iterator.next.$cast)
       |              )
       |          case 'd' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
       |                m1.values.iterator.next.$cast.compareTo(
       |                  m2.values.iterator.next.$cast)
       |              )
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }
}
