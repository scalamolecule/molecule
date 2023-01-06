package molecule.db.datomic.transaction

import java.util.Collections
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.{Insert, Insert2Data, InsertResolvers_, Save}
import molecule.core.util.ModelUtils
import molecule.core.validation.CheckConflictingAttrs
import scala.collection.mutable.ListBuffer


trait Insert_stmts
  extends DatomicTxBase_JVM
    with Insert2Data
    with DatomicDataType_JVM
    with ModelUtils { self: Insert with InsertResolvers_ =>

  override protected val prevRefs: ListBuffer[AnyRef] = new ListBuffer[AnyRef]

  def getStmts(
    elements: Seq[Element],
    tpls: Seq[Product],
    debug: Boolean = true
  ): Data = {
    initTxBase(elements)
    if (debug) {
      println("\n\n--- INSERT -----------------------------------------------------------------------")
      elements.foreach(println)
    }
    // Resolve tx meta elements separately and merge append
    val (mainElements, txMetaElements) = elements.last match {
      case TxMetaData(txMetaElements) => (elements.init, txMetaElements)
      case _                          => (elements, Nil)
    }
    CheckConflictingAttrs(mainElements)
    val tpl2stmts = getResolver(mainElements)
    tpls.foreach { tpl =>
      e = newId
      e0 = e
      tpl2stmts(tpl)
    }

    if (txMetaElements.nonEmpty) {
      val txMetaStmts = (new Save(true) with Save_stmts)
        .getRawStmts(txMetaElements, datomicTx, false)
      stmts.addAll(txMetaStmts)
    }
    if (debug) {
      println("---")
      stmts.forEach(stmt => println(stmt))
    }
    stmts
  }

  override protected def addComposite(
    tplIndex: Int,
    compositeElements: Seq[Element]
  ): Product => Unit = {
    hasComposites = true
    val composite2stmts = getResolver(compositeElements)
    // Start from initial entity id for each composite sub group
    countValueAttrs(compositeElements) match {
      case 1 => (tpl: Product) =>
        e = e0
        composite2stmts(Tuple1(tpl.productElement(tplIndex)))
      case _ => (tpl: Product) =>
        e = e0
        composite2stmts(tpl.productElement(tplIndex).asInstanceOf[Product])
    }
  }

  override protected def addNested(
    tplIndex: Int,
    ns: String,
    refAttr: String,
    nestedElements: Seq[Element]
  ): Product => Unit = {
    // Recursively resolve nested levels
    val nested2stmts = getResolver(nestedElements)
    countValueAttrs(nestedElements) match {
      case 1 => // Single nested values
        (tpl: Product) => {
          val nestedValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val nestedBaseId = e
          nestedValues.foreach { nestedValue =>
            e = nestedBaseId
            val tpl = Tuple1(nestedValue)
            addRef(ns, refAttr)(tpl)
            e0 = e
            nested2stmts(tpl)
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls   = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val nestedBaseId = e
          nestedTpls.foreach { nestedTpl =>
            e = nestedBaseId
            addRef(ns, refAttr)(nestedTpl)
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
    }
  }

  override protected def addV(ns: String, attr: String, tplIndex: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      appendStmt(add, e, a, value(tpl.productElement(tplIndex)).asInstanceOf[AnyRef])
    }
  }
  override protected def addOptV(ns: String, attr: String, tplIndex: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(tplIndex) match {
        case Some(v) => appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
        case None    => // no statement to insert
      }
    }
  }
  override protected def addTxV(ns: String, attr: String, tplIndex: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      e = datomicTx
      backRefs = backRefs + (ns -> e)
      appendStmt(add, e, a, value(tpl.productElement(tplIndex)).asInstanceOf[AnyRef])
    }
  }

  override protected def addSet(ns: String, attr: String, tplIndex: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(tplIndex).asInstanceOf[Set[Any]].foreach { v =>
        appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
      }
    }
  }
  override protected def addOptSet(ns: String, attr: String, tplIndex: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) =>
          set.foreach { v =>
            appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
          }
        case None              => // no statement to insert
      }
    }
  }

  override protected def addRef(ns: String, refAttr: String): Product => Unit = {
    val a = kw(ns, refAttr)
    (_: Product) =>
      backRefs = backRefs + (ns -> e)
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      e = newId
      stmt.add(e)
      stmts.add(stmt)
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    (_: Product) => e = backRefs(backRefNs)
  }

  override protected lazy val valueString = identity

  // Save Int as Long in Datomic since we can't enforce Integers in edn (for JS rpc)
  override protected lazy val valueInt        = (v: Any) => v.asInstanceOf[Int].toLong
  override protected lazy val valueLong       = identity
  override protected lazy val valueFloat      = identity
  override protected lazy val valueDouble     = identity
  override protected lazy val valueBoolean    = boolean2java
  override protected lazy val valueBigInt     = bigInt2java
  override protected lazy val valueBigDecimal = bigDec2java
  override protected lazy val valueDate       = identity
  override protected lazy val valueUUID       = identity
  override protected lazy val valueURI        = identity
  override protected lazy val valueByte       = byte2java
  override protected lazy val valueShort      = short2java
  override protected lazy val valueChar       = char2java
}