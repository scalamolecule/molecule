package codegen.datomic.query

import codegen.DatomicGenBase

object _SortOne extends DatomicGenBase("SortOne", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datomic.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Integer => jInteger, Long => jLong, Float => jFloat}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.boilerplate.ast.Model._
       |
       |
       |trait $fileName_[Tpl] { self: Base[Tpl] =>
       |$sorters
       |}""".stripMargin
  }

  def sorter(tpe: String): String = {
    val javaTpe = javaTypes(tpe)
    s"""
       |  protected def sortOne$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
       |    attr.sort.map { sort =>
       |      (
       |        sort.last.toString.toInt,
       |        sort.head match {
       |          case 'a' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              a.get(i).asInstanceOf[$javaTpe].compareTo(b.get(i).asInstanceOf[$javaTpe])
       |          case 'd' => (nestedIdsCount: Int) =>
       |            val i = nestedIdsCount + attrIndex
       |            (a: Row, b: Row) =>
       |              b.get(i).asInstanceOf[$javaTpe].compareTo(a.get(i).asInstanceOf[$javaTpe])
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }
}
