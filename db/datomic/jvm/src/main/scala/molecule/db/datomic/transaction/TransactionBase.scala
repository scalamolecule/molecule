package molecule.db.datomic.transaction

import java.util
import java.util.{List => jList}
import java.lang.{Boolean => jBoolean}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.util.fns
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

abstract class TransactionBase(elements: Seq[Element]) {

  // Accumulate java insertion data
  final protected val stmts: util.ArrayList[jList[AnyRef]] = new util.ArrayList[jList[AnyRef]]()

  protected def unexpected(element: Element) = throw MoleculeException("Unexpected element: " + element)

  protected val nsFull       : String              = getNs(elements)
  protected val part         : String              = fns.partNs(nsFull).head
  protected var tempId       : Int                 = 0
  protected var lowest       : Int                 = 0
  protected var e0           : String              = ""
  protected var e            : String              = ""
  protected var stmt         : jList[AnyRef]       = null
  protected var backRefs     : Map[String, String] = Map.empty[String, String]
  protected val prevRefs     : ListBuffer[String]  = new ListBuffer[String]
  protected var hasComposites: Boolean             = false

  protected def stmtList = new util.ArrayList[AnyRef](4)

  protected def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }

  protected def kw(ns: String, attr: String) = Keyword.intern(ns, attr)
  protected lazy val add     = kw("db", "add")
  protected lazy val retract = kw("db", "retract")
  protected lazy val tx      = "datomic.tx"

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

  private def dup(element: String) = throw MoleculeException(
    "Can't transact duplicate element: " + element
  )

  @tailrec
  final protected def checkConflictingAttributes(
    elements: Seq[Element],
    prev: Array[Array[Array[String]]] = Array(Array(Array.empty[String])),
    level: Int = 0,
    group: Int = 0,
    refPath: Seq[String] = Seq("")
  ): Unit = {
    elements match {
      case head :: tail => head match {
        case a: Attr =>
          val attr         = a.ns + "." + a.attr
          // Distinguish multiple ref paths to the same namespace
          val attrPrefixed = refPath.mkString("-") + "-" + attr
          if (prev(level)(group).contains(attrPrefixed))
            dup(attr)
          prev(level)(group) = prev(level)(group) :+ attrPrefixed
          checkConflictingAttributes(tail, prev, level, group, refPath)

        case r: Ref =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          prev(level) = prev(level) :+ Array(ref)
          checkConflictingAttributes(tail, prev, level, group + 1, refPath :+ ref)

        case _: BackRef =>
          if (group == 0)
            throw MoleculeException("Can't use backref from here.")
          checkConflictingAttributes(tail, prev, level, group - 1, refPath.init)

        case Composite(es) =>
          checkConflictingAttributes(es ++ tail, prev)

        case Nested(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          checkConflictingAttributes(es ++ tail, prev1, level + 1, 0, refPath)

        case NestedOpt(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          checkConflictingAttributes(es ++ tail, prev1, level + 1, 0, refPath)

        case TxMetaData(txElements) =>
          val prev1 = prev :+ Array(Array.empty[String])
          checkConflictingAttributes(txElements, prev1, level + 1, 0, Nil)

        case other =>
          throw MoleculeException("StmtsBuilder: Unexpected  element: " + other)
      }
      case Nil          => ()
    }
  }
}
