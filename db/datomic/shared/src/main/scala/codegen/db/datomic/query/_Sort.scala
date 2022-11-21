package codegen.db.datomic.query

import codegen.DatomicGenBase

object _Sort extends DatomicGenBase("Sort", "/query") {

  val content = {
    val sorters = baseTypes.map(sorter).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query
       |
       |import java.lang.{Boolean => jBoolean, Double => jDouble, Integer => jInteger, Long => jLong}
       |import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.boilerplate.ast.MoleculeModel._
       |
       |
       |trait ${fileName}_[Tpl] { self: Base[Tpl] =>
       |$sorters
       |}""".stripMargin
  }

  def sorter(tpe: String): String = {
    val javaTpe = javaTypes(tpe)
    s"""
       |  protected def sort$tpe(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
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
