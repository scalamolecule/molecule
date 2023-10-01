package molecule.datalog.datomic.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps

trait Save_datomic
  extends DatomicBase_JVM
    with SaveOps
    with MoleculeLogging { self: ResolveSave =>

  def getRawStmts(
    elements: List[Element],
    id: String,
    debug: Boolean = true,
    init: Boolean = true,
    idIndex: Int = 0
  ): Data = {
    //    elements.foreach(println)
    if (init) {
      initTxBase(elements, idIndex)
    }
    e = id
    e0 = e

    handleRefNs(getInitialNs(elements))

    // populate `stmts`
    resolve(elements)
    if (debug) {
      val saveStrs = "SAVE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
      logger.debug(saveStrs.mkString("\n").trim)
    }
    stmts
  }

  def getStmts(elements: List[Element], idIndex: Int = 0): Data = {
    initTxBase(elements, idIndex)
    getRawStmts(elements, newId, init = false)
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T],
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    optValue.foreach { v =>
      appendStmt(add, e, kw(ns, attr), v.asInstanceOf[AnyRef])
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[Any]],
    transformValue: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optSet.foreach { set =>
      val a = kw(ns, attr)
      set.foreach { v =>
        appendStmt(add, e, a, v.asInstanceOf[AnyRef])
      }
    }
  }

  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card
  ): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(kw(ns, refAttr))
    e = newId
    stmt.add(e)
    stmts.add(stmt)
    handleRefNs(refNs)
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    e = backRefs(backRefNs)
  }

  override protected def handleRefNs(refNs: String): Unit = {
    backRefs = backRefs + (refNs -> e)
  }

  // Save Int as Long in Datomic
  override protected lazy val transformString     = (v: String) => v
  override protected lazy val transformInt        = (v: Int) => v
  override protected lazy val transformLong       = (v: Long) => v
  override protected lazy val transformFloat      = (v: Float) => v
  override protected lazy val transformDouble     = (v: Double) => v
  override protected lazy val transformBoolean    = (v: Boolean) => v
  override protected lazy val transformBigInt     = (v: BigInt) => v.bigInteger
  override protected lazy val transformBigDecimal = (v: BigDecimal) => v.bigDecimal
  override protected lazy val transformDate       = (v: Date) => v
  override protected lazy val transformUUID       = (v: UUID) => v
  override protected lazy val transformURI        = (v: URI) => v
  override protected lazy val transformByte       = (v: Byte) => v.toInt
  override protected lazy val transformShort      = (v: Short) => v.toInt
  override protected lazy val transformChar       = (v: Char) => v.toString

  override protected lazy val extsString     = List("", "AnyRef")
  override protected lazy val extsInt        = List("", "AnyRef")
  override protected lazy val extsLong       = List("", "AnyRef")
  override protected lazy val extsFloat      = List("", "AnyRef")
  override protected lazy val extsDouble     = List("", "AnyRef")
  override protected lazy val extsBoolean    = List("", "AnyRef")
  override protected lazy val extsBigInt     = List("", "AnyRef")
  override protected lazy val extsBigDecimal = List("", "AnyRef")
  override protected lazy val extsDate       = List("", "AnyRef")
  override protected lazy val extsUUID       = List("", "AnyRef")
  override protected lazy val extsURI        = List("", "AnyRef")
  override protected lazy val extsByte       = List("", "AnyRef")
  override protected lazy val extsShort      = List("", "AnyRef")
  override protected lazy val extsChar       = List("", "AnyRef")
}