package molecule.datomic.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error.ValidationErrors
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.{SaveExtraction, SaveOps}
import molecule.core.validation.ConflictingAttrs

trait Save_stmts extends DatomicTxBase_JVM with SaveOps with MoleculeLogging { self: SaveExtraction =>

  def getRawStmts(
    elements: List[Element],
    eid: String,
    debug: Boolean = true,
    init: Boolean = true
  ): Data = {
    if (init) {
      initTxBase(elements)
    }
    val validationErrors = ConflictingAttrs.check(elements)
    if (validationErrors.nonEmpty) {
      throw ValidationErrors(validationErrors)
    }
    e = eid
    e0 = e

    // populate `stmts`
    resolve(elements)

    if (debug) {
      val saveStrs = "SAVE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
      logger.debug(saveStrs.mkString("\n").trim)
    }
    stmts
  }

  def getStmts(elements: List[Element]): Data = {
    initTxBase(elements)
    getRawStmts(elements, newId, init = false)
  }


  override protected def handleNs(ns: String): Unit = {
    backRefs = backRefs + (ns -> e)
  }
  override protected def handleComposite(isInsertTxMetaData: Boolean): Unit = {
    e = if (isInsertTxMetaData) datomicTx else e0
  }
  override protected def handleTxMetaData(): Unit = {
    e = datomicTx
    e0 = datomicTx
  }

  override protected def addV(ns: String, attr: String, optValue: Option[Any]): Unit = {
    optValue.foreach { v =>
      appendStmt(add, e, kw(ns, attr), v.asInstanceOf[AnyRef])
    }
  }

  override protected def addSet[T](ns: String, attr: String, optSet: Option[Set[T]]): Unit = {
    optSet.foreach { set =>
      val a = kw(ns, attr)
      set.foreach { v =>
        appendStmt(add, e, a, v.asInstanceOf[AnyRef])
      }
    }
  }

  override protected def backRef(backRefNs: String): Unit = {
    e = backRefs(backRefNs)
  }

  override protected def ref(ns: String, refAttr: String): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(kw(ns, refAttr))
    e = newId
    stmt.add(e)
    stmts.add(stmt)
  }

  // Save Int as Long in Datomic
  override protected lazy val valueString     = (v: String) => v
  override protected lazy val valueInt        = (v: Int) => v //.toLong
  override protected lazy val valueLong       = (v: Long) => v
  override protected lazy val valueFloat      = (v: Float) => v
  override protected lazy val valueDouble     = (v: Double) => v
  override protected lazy val valueBoolean    = (v: Boolean) => v
  override protected lazy val valueBigInt     = (v: BigInt) => v.bigInteger
  override protected lazy val valueBigDecimal = (v: BigDecimal) => v.bigDecimal
  override protected lazy val valueDate       = (v: Date) => v
  override protected lazy val valueUUID       = (v: UUID) => v
  override protected lazy val valueURI        = (v: URI) => v
  override protected lazy val valueByte       = (v: Byte) => v.toInt
  override protected lazy val valueShort      = (v: Short) => v.toInt
  override protected lazy val valueChar       = (v: Char) => v.toString
}