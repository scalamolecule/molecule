package molecule.db.datomic.transaction

import java.lang.{Boolean => jBoolean}
import java.util.{ArrayList => jArrayList, List => jList}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.util.fns
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait DatomicTxBase_JVM extends DatomicDataType_JVM {

  protected def initTxBase(elements: Seq[Element]): Unit = {
    nsFull = getInitialNs(elements)
    part = fns.partNs(nsFull).head
  }

  // Accumulate java insertion data
  final protected val stmts: jArrayList[jList[AnyRef]] = new jArrayList[jList[AnyRef]]()

  protected var nsFull       : String              = ""
  protected var part         : String              = ""
  protected var tempId       : Int                 = 0
  protected var lowest       : Int                 = 0
  protected var e            : AnyRef              = "" // Long or String (#db/id[db.part/user -1])
  protected var e0           : AnyRef              = ""
  protected var stmt         : jList[AnyRef]       = null
  protected var backRefs     : Map[String, AnyRef] = Map.empty[String, AnyRef]
  protected val prevRefs     : ListBuffer[AnyRef]  = new ListBuffer[AnyRef]
  protected var hasComposites: Boolean             = false

  protected lazy val add           = kw("db", "add")
  protected lazy val retract       = kw("db", "retract")
  protected lazy val retractEntity = kw("db", "retractEntity")
  protected lazy val dbId          = kw("db", "id")
  protected lazy val datomicTx     = "datomic.tx"

  protected lazy val bigInt2java  = (v: Any) => v.asInstanceOf[BigInt].bigInteger
  protected lazy val bigDec2java  = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal
  protected lazy val char2java    = (v: Any) => v.toString
  protected lazy val byte2java    = (v: Any) => v.asInstanceOf[Byte].toInt
  protected lazy val short2java   = (v: Any) => v.asInstanceOf[Short].toInt
  protected lazy val boolean2java = (v: Any) => v.asInstanceOf[Boolean].asInstanceOf[jBoolean]


  protected def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }
  protected def kw(ns: String, attr: String) = Keyword.intern(ns, attr)

  protected def stmtList = new jArrayList[AnyRef](4)
  protected def appendStmt(
    op: Keyword,
    e: AnyRef,
    a: Keyword,
    v: AnyRef,
  ): Unit = {
    val addStmt = stmtList
    addStmt.add(op)
    addStmt.add(e)
    addStmt.add(a)
    addStmt.add(v)
    stmts.add(addStmt)
  }
  protected def addRetractEntityStmt(eid: AnyRef) = {
    val stmt = new jArrayList[AnyRef](2)
    stmt.add(retractEntity)
    stmt.add(eid)
    stmts.add(stmt)
  }

  @tailrec
  final protected def getInitialNs(elements: Seq[Element]): String = elements.head match {
    case a: Attr       => a.ns
    case b: Ref        => b.ns
    case Composite(es) => getInitialNs(es)
    case other         => throw MoleculeException("Unexpected head element: " + other)
  }
}
