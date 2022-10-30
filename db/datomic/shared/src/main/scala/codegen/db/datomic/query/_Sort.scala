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
       |trait $fileName[Tpl] { self: Base[Tpl] =>
       |$sorters
       |}""".stripMargin
  }

  def sorter(tpe: String): String = {
    val javaTpe = javaTypes(tpe)
    s"""
       |  protected def sort$tpe(atom: Atom, attrIndex: Int): Option[(Int, (Row, Row) => Int)] = {
       |    atom.sort.map { sort =>
       |      (
       |        sort.last.toInt,
       |        sort.head match {
       |          case 'a' => (a: Row, b: Row) =>
       |            a.get(attrIndex).asInstanceOf[$javaTpe].compareTo(b.get(attrIndex).asInstanceOf[$javaTpe])
       |          case 'd' => (a: Row, b: Row) =>
       |            b.get(attrIndex).asInstanceOf[$javaTpe].compareTo(a.get(attrIndex).asInstanceOf[$javaTpe])
       |        }
       |      )
       |    }
       |  }""".stripMargin
  }

  def sorterInt: String = {
    s"""
       |  protected def sortInt(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
       |    (
       |      sort.last.toInt,
       |      sort.head match {
       |        case 'a' => (a: Row, b: Row) =>
       |          a.get(attrIndex) match {
       |            case a: jInteger => a.compareTo(b.get(attrIndex).asInstanceOf[jInteger])
       |            case a: jLong    => a.compareTo(b.get(attrIndex).asInstanceOf[jLong])
       |          }
       |        case 'd' => (a: Row, b: Row) =>
       |          b.get(attrIndex) match {
       |            case b: jInteger => b.compareTo(a.get(attrIndex).asInstanceOf[jInteger])
       |            case b: jLong    => b.compareTo(a.get(attrIndex).asInstanceOf[jLong])
       |          }
       |      }
       |    )
       |  }""".stripMargin
  }
}