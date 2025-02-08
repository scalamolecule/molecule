package molecule.datalog.datomic.transaction

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.ast._
import molecule.core.ast.DataModel.Element
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.{JavaConversions, MoleculeLogging}

trait Save_datomic
  extends DatomicBase_JVM
    with SaveOps
    with JavaConversions
    with MoleculeLogging { self: ResolveSave =>

  def getRawStmts(
    elements: List[Element],
    id: String,
    debug: Boolean = true,
    init: Boolean = true,
    idIndex: Int = 0
  ): Data = {
    if (init) {
      initTxBase(elements, idIndex)
    }
    e = id
    e0 = e

    handleRef(getInitialEntity(elements))

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
    ent: String,
    attr: String,
    optValue: Option[T],
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    optValue.foreach { v =>
      appendStmt(add, e, kw(ent, attr), transformValue(v).asInstanceOf[AnyRef])
    }
  }

  override protected def addSet[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optSet.foreach { set =>
      val a = kw(ent, attr)
      set.foreach { v =>
        appendStmt(add, e, a, transformValue(v).asInstanceOf[AnyRef])
      }
    }
  }

  override protected def addSeq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optSeq.foreach { seq =>
      val a   = kw(ent, attr)
      val a_i = kw(s"$ent.$attr", "i_")
      val a_v = kw(s"$ent.$attr", "v_")
      unusedRefIds -= e
      usedRefIds += e
      var i = 0
      seq.foreach { v =>
        val ref = newId
        appendStmt(add, e, a, ref)
        appendStmt(add, ref, a_i, i.asInstanceOf[AnyRef])
        appendStmt(add, ref, a_v, transformValue(v).asInstanceOf[AnyRef])
        i += 1
      }
    }
  }

  override protected def addByteArray(
    ent: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit = {
    optArray.foreach { array =>
      appendStmt(add, e, kw(ent, attr), array.asInstanceOf[AnyRef])
    }
  }

  override protected def addMap[T](
    ent: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optMap.foreach { map =>
      val a   = kw(ent, attr)
      val a_k = kw(s"$ent.$attr", "k_")
      val a_v = kw(s"$ent.$attr", "v_")
      unusedRefIds -= e
      usedRefIds += e
      var i = 0
      map.foreach { case (k, v) =>
        val ref = newId
        appendStmt(add, e, a, ref)
        appendStmt(add, ref, a_k, validKey(k).asInstanceOf[AnyRef])
        appendStmt(add, ref, a_v, transformValue(v).asInstanceOf[AnyRef])
        i += 1
      }
    }
  }

  override protected def addRef(
    ent: String,
    refAttr: String,
    ref: String,
    card: Card,
  ): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(kw(ent, refAttr))
    e = newId
    stmt.add(e)
    stmts.add(stmt)
    handleRef(ref)
  }

  override protected def addBackRef(backRef: String): Unit = {
    e = backRefs(backRef)
  }

  override protected def handleRef(ref: String): Unit = {
    backRefs = backRefs + (ref -> e)
  }

  // Save Int as Long in Datomic
  override protected lazy val transformID             = (v: Long) => v
  override protected lazy val transformString         = (v: String) => v
  override protected lazy val transformInt            = (v: Int) => v
  override protected lazy val transformLong           = (v: Long) => v
  override protected lazy val transformFloat          = (v: Float) => v
  override protected lazy val transformDouble         = (v: Double) => v
  override protected lazy val transformBoolean        = (v: Boolean) => v
  override protected lazy val transformBigInt         = (v: BigInt) => v.bigInteger
  override protected lazy val transformBigDecimal     = (v: BigDecimal) => v.bigDecimal
  override protected lazy val transformDate           = (v: Date) => v
  override protected lazy val transformDuration       = (v: Duration) => v.toString
  override protected lazy val transformInstant        = (v: Instant) => v.toString
  override protected lazy val transformLocalDate      = (v: LocalDate) => v.toString
  override protected lazy val transformLocalTime      = (v: LocalTime) => v.toString
  override protected lazy val transformLocalDateTime  = (v: LocalDateTime) => v.toString
  override protected lazy val transformOffsetTime     = (v: OffsetTime) => v.toString
  override protected lazy val transformOffsetDateTime = (v: OffsetDateTime) => v.toString
  override protected lazy val transformZonedDateTime  = (v: ZonedDateTime) => v.toString
  override protected lazy val transformUUID           = (v: UUID) => v
  override protected lazy val transformURI            = (v: URI) => v
  override protected lazy val transformByte           = (v: Byte) => v.toInt
  override protected lazy val transformShort          = (v: Short) => v.toInt
  override protected lazy val transformChar           = (v: Char) => v.toString
}