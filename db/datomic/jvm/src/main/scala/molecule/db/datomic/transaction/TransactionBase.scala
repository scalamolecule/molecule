package molecule.db.datomic.transaction

import java.util
import java.util.{List => jList}
import java.lang.{Boolean => jBoolean}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.util.fns
import scala.annotation.tailrec

abstract class TransactionBase(elements: Seq[Element]) {

  // Accumulate java insert data
  final protected val stmts: util.ArrayList[jList[AnyRef]] = new java.util.ArrayList[jList[AnyRef]]()

  protected def unexpected(element: Element) = throw MoleculeException("Unexpected element: " + element)

  protected val nsFull  : String              = getNs(elements)
  protected val part    : String              = fns.partNs(nsFull).head
  protected var tempId  : Int                 = 0 // tempIdInit
  protected var lowest  : Int                 = 0 // tempIdInit
  protected var e       : String              = ""
  protected var stmt    : jList[AnyRef]       = null
  protected var backRefs: Map[String, String] = Map.empty[String, String]

  protected def stmtList = new java.util.ArrayList[AnyRef](4)


  protected def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }
  protected def prevId: String = {
    tempId += 1
    "#db/id[" + part + " " + tempId + "]"
  }

  protected def kw(ns: String, attr: String) = Keyword.intern(ns, attr)
  protected lazy val add     = kw("db", "add")
  protected lazy val retract = kw("db", "retract")

  protected lazy val bigInt2java  = (v: Any) => v.asInstanceOf[BigInt].bigInteger
  protected lazy val bigDec2java  = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal
  protected lazy val char2java    = (v: Any) => v.toString
  protected lazy val byte2java    = (v: Any) => v.asInstanceOf[Byte].toInt
  protected lazy val short2java   = (v: Any) => v.asInstanceOf[Short].toInt
  protected lazy val boolean2java = (v: Any) => v.asInstanceOf[Boolean].asInstanceOf[jBoolean]


  @tailrec
  final protected def getNs(elements: Seq[Element]): String = elements.head match {
    case a: Attr       => a.ns
    case b: Ref        => b.ns
    case Composite(es) => getNs(es)
    case other         =>
      throw MoleculeException("StmtsBuilder: Unexpected head element: " + other)
  }

}
