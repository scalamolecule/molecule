package molecule.datalog.datomic.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST.Card
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.SaveExtraction
import molecule.core.transaction.ops.SaveOps

trait Data_Save extends DatomicBase_JVM with SaveOps with MoleculeLogging { self: SaveExtraction =>

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

    handleNs(getInitialNs(elements))

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
    handleValue: T => Any
  ): Unit = {
    optValue.foreach { v =>
      appendStmt(add, e, kw(ns, attr), v.asInstanceOf[AnyRef])
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[T]],
    handleValue: T => Any,
    //    set2array: Set[T] => Array[AnyRef],
    set2array: Set[Any] => Array[AnyRef],
  ): Unit = {
    optSet.foreach { set =>
      val a = kw(ns, attr)
      set.foreach { v =>
        //        println(v)
        //        println(v.getClass)
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
    handleNs(refNs)
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    e = backRefs(backRefNs)
  }

  override protected def handleNs(ns: String): Unit = {
    backRefs = backRefs + (ns -> e)
  }
  override protected def handleComposite(isInsertTxMetaData: Boolean): Unit = {
    e = if (isInsertTxMetaData) datomicTx else e0
  }
  override protected def handleTxMetaData(ns: String): Unit = {
    e = datomicTx
    e0 = datomicTx
    handleNs(ns)
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

  override protected lazy val handleString     = (v: Any) => v
  override protected lazy val handleInt        = (v: Any) => v
  override protected lazy val handleLong       = (v: Any) => v
  override protected lazy val handleFloat      = (v: Any) => v
  override protected lazy val handleDouble     = (v: Any) => v
  override protected lazy val handleBoolean    = (v: Any) => v
  override protected lazy val handleBigInt     = (v: Any) => v.asInstanceOf[BigInt].bigInteger
  override protected lazy val handleBigDecimal = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal
  override protected lazy val handleDate       = (v: Any) => v
  override protected lazy val handleUUID       = (v: Any) => v
  override protected lazy val handleURI        = (v: Any) => v
  override protected lazy val handleByte       = (v: Any) => v.asInstanceOf[Byte].toInt
  override protected lazy val handleShort      = (v: Any) => v.asInstanceOf[Short].toInt
  override protected lazy val handleChar       = (v: Any) => v.toString

  override protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.map(_.toString.toDouble.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigInt]].map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigDecimal]].map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
}