package molecule.db.datomic.transaction

import java.lang.{Boolean => jBoolean}
import java.util.{ArrayList => jArrayList, List => jList}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.util.fns
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

abstract class DatomicTransactionBase(elements: Seq[Element]) {

  // Accumulate java insertion data
  final protected val stmts: jArrayList[jList[AnyRef]] = new jArrayList[jList[AnyRef]]()

  protected def unexpected(element: Element) = throw MoleculeException("Unexpected element: " + element)

  protected val nsFull       : String              = getNs(elements)
  protected val part         : String              = fns.partNs(nsFull).head
  protected var tempId       : Int                 = 0
  protected var lowest       : Int                 = 0
  protected var e            : AnyRef              = "" // Long or String (#db/id[db.part/user -1])
  protected var e0           : AnyRef              = ""
  protected var stmt         : jList[AnyRef]       = null
  protected var backRefs     : Map[String, AnyRef] = Map.empty[String, AnyRef]
  protected val prevRefs     : ListBuffer[AnyRef]  = new ListBuffer[AnyRef]
  protected var hasComposites: Boolean             = false

  protected def stmtList = new jArrayList[AnyRef](4)

  protected def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }

  protected def kw(ns: String, attr: String) = Keyword.intern(ns, attr)
  protected lazy val add       = kw("db", "add")
  protected lazy val retract   = kw("db", "retract")
  protected lazy val dbId      = kw("db", "id")
  protected lazy val datomicTx = "datomic.tx"

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
    case other         => throw MoleculeException("Unexpected head element: " + other)
  }

  private def dup(element: String) = throw MoleculeException(s"Can't transact duplicate attribute `$element`.")

  @tailrec
  final protected def checkConflictingAttributes(
    elements: Seq[Element],
    prev: Array[Array[Array[String]]] = Array(Array(Array.empty[String])),
    level: Int = 0,
    group: Int = 0,
    refPath: Seq[String] = Seq(""),
    distinguishMode: Boolean = false
  ): Unit = {
    elements match {
      case head :: tail => head match {
        case a: Attr =>
          val attr         = a.ns + "." + a.attr
          // Distinguish multiple ref paths to the same namespace
          val attrPrefixed = if (distinguishMode) {
            val mode = a match {
              case _: AttrOneMan => "man"
              case _: AttrOneOpt => "opt"
              case _: AttrOneTac => "tac"
              case _: AttrSetMan => "man"
              case _: AttrSetOpt => "opt"
              case _: AttrSetTac => "tac"
            }
            refPath.mkString("-") + "-" + attr + "-" + mode
          } else {
            refPath.mkString("-") + "-" + attr
          }
          if (prev(level)(group).contains(attrPrefixed))
            dup(attr)
          prev(level)(group) = prev(level)(group) :+ attrPrefixed
          checkConflictingAttributes(tail, prev, level, group, refPath, distinguishMode)

        case r: Ref =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref)) {
            dup(ref)
          }
          if (refPath.contains(ref)) {
            dup(ref)
          }
          prev(level) = prev(level) :+ Array(ref)
          checkConflictingAttributes(tail, prev, level, group + 1, refPath :+ ref, distinguishMode)

        case _: BackRef =>
          if (group == 0)
            throw MoleculeException("Can't use backref from here.")
          checkConflictingAttributes(tail, prev, level, group - 1, refPath.init, distinguishMode)

        case Composite(es) =>
          checkConflictingAttributes(es ++ tail, prev, level, group, Seq(""), distinguishMode)

        case Nested(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          checkConflictingAttributes(es ++ tail, prev1, level + 1, 0, refPath, distinguishMode)

        case NestedOpt(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          checkConflictingAttributes(es ++ tail, prev1, level + 1, 0, refPath, distinguishMode)

        case TxMetaData(txElements) =>
          val prev1 = prev :+ Array(Array.empty[String])
          checkConflictingAttributes(txElements, prev1, level + 1, 0, Nil, distinguishMode)

        case other =>
          throw MoleculeException("Unexpected  element: " + other)
      }
      case Nil          => ()
    }
  }
}
