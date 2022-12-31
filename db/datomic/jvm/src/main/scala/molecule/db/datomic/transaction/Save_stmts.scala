package molecule.db.datomic.transaction

import java.util.{ArrayList => jArrayList, List => jList}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.{Save, Save2Data}
import molecule.core.validation.CheckConflictingAttrs

trait Save_stmts extends DatomicTxBase_JVM with Save2Data { self: Save =>

  def getRawStmts(
    elements: Seq[Element],
    eid: String,
    debug: Boolean = true
  ): jArrayList[jList[AnyRef]] = {
    initTxBase(elements)
    if (debug) {
      println("\n\n--- SAVE -----------------------------------------------------------------------")
      elements.foreach(println)
    }
    CheckConflictingAttrs(elements)
    e = eid
    e0 = e
    resolve(elements)

    if (debug) {
      println("---")
      stmts.forEach(stmt => println(stmt))
    }
    stmts
  }

  def getStmts(elements: Seq[Element]): Data = getRawStmts(elements, newId)


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

  override protected lazy val valueString     = identity
  override protected lazy val valueInt        = identity
  override protected lazy val valueLong       = identity
  override protected lazy val valueFloat      = identity
  override protected lazy val valueDouble     = identity
  override protected lazy val valueBoolean    = identity
  override protected lazy val valueBigInt     = (v: BigInt) => v.bigInteger
  override protected lazy val valueBigDecimal = (v: BigDecimal) => v.bigDecimal
  override protected lazy val valueDate       = identity
  override protected lazy val valueUUID       = identity
  override protected lazy val valueURI        = identity
  override protected lazy val valueByte       = (v: Byte) => v.toInt
  override protected lazy val valueShort      = (v: Short) => v.toInt
  override protected lazy val valueChar       = (v: Char) => v.toString
}