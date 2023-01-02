package molecule.db.datomic.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.{Save, Save2Data}
import molecule.core.validation.CheckConflictingAttrs

trait Save_edn extends DatomicTxBase_JS with Save2Data { self: Save =>

  def getRawEdn(
    elements: Seq[Element],
    eid: String,
    debug: Boolean = true,
    init: Boolean = true,
  ): Data = {
    if (init)
      initTxBase(elements)
    if (debug) {
      println("\n\n--- SAVE -----------------------------------------------------------------------")
      elements.foreach(println)
    }
    CheckConflictingAttrs(elements)
    e = eid
    e0 = e
    resolve(elements)
    s"[${buf.toString}\n]"
  }

  def getEdn(elements: Seq[Element]): Data = {
    initTxBase(elements)
    getRawEdn(elements, newId, init = false)
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
      addStmt(e, kw(ns, attr), v)
    }
  }

  override protected def addSet[T](ns: String, attr: String, optSet: Option[Set[T]]): Unit = {
    optSet.foreach { set =>
      val a = kw(ns, attr)
      set.foreach { v =>
        addStmt(e, a, v)
      }
    }
  }

  override protected def backRef(backRefNs: String): Unit = {
    e = backRefs(backRefNs)
  }

  override protected def ref(ns: String, refAttr: String): Unit = {
    val a = kw(ns, refAttr)
    buf.append(s"\n [:db/add $e $a ")
    e = newId
    buf.append(s"$e]")
  }

  override protected lazy val valueString     = (v: String) => quote(v)
  override protected lazy val valueInt        = (v: Int) => v
  override protected lazy val valueLong       = (v: Long) => v
  override protected lazy val valueFloat      = (v: Float) => v.toString + (if (v.isWhole) ".0" else "")
  override protected lazy val valueDouble     = (v: Double) => v.toString + (if (v.isWhole) ".0" else "")
  override protected lazy val valueBoolean    = (v: Boolean) => v
  override protected lazy val valueBigInt     = (v: BigInt) => v.toString + "N"
  override protected lazy val valueBigDecimal = (v: BigDecimal) => v.toString + (if (v.isWhole) ".0M" else "M")
  override protected lazy val valueDate       = (v: Date) => "#inst \"" + date2datomicStr2(v) + "\""
  override protected lazy val valueUUID       = (v: UUID) => "#uuid \"" + v + "\""
  override protected lazy val valueURI        = (v: URI) => v
  override protected lazy val valueByte       = (v: Byte) => v
  override protected lazy val valueShort      = (v: Short) => v
  override protected lazy val valueChar       = (v: Char) => "\"" + v + "\""
}