package codegen.db.datomic.query

import codegen.DatomicGenBase

object _SortOneOptFlat extends DatomicGenBase("SortOneOptFlat", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.boilerplate.ast.Model._
       |
       |
       |trait ${fileName}_[Tpl] { self: Base[Tpl] =>
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
    val javaTpe = javaTypes(tpe)
    s"""
       |  protected def sortOneOptFlat$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
       |    attr.sort.map { sort =>
       |      (
       |        sort.last.toString.toInt,
       |        sort.head match {
       |          case 'a' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(a, b, i, (v1, v2) => v1.asInstanceOf[$javaTpe].compareTo(v2.asInstanceOf[$javaTpe]))
       |          case 'd' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              compare(b, a, i, (v1, v2) => v1.asInstanceOf[$javaTpe].compareTo(v2.asInstanceOf[$javaTpe]))
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }
}
