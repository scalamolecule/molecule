package molecule.db.datomic.transaction

import java.util.Collections
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.{Insert, Insert2Data, InsertResolvers_, Save}
import molecule.core.validation.CheckConflictingAttrs
import scala.collection.mutable.ListBuffer


trait Insert_stmts
  extends DatomicTxBase_JVM
    with Insert2Data
    with DatomicDataType_JVM { self: Insert with InsertResolvers_ =>

  override protected val prevRefs: ListBuffer[AnyRef] = new ListBuffer[AnyRef]


  def getStmts(elements: Seq[Element], data: Seq[Product]): Data = {
    // Resolve tx meta elements separately and merge append
    val (mainElements, txMetaElements) = elements.last match {
      case TxMetaData(txMetaElements) => (elements.init, txMetaElements)
      case _                          => (elements, Nil)
    }
    CheckConflictingAttrs(mainElements)
    val tpl2stmts = getResolver(mainElements)
    data.foreach { tpl =>
      e = newId
      e0 = e
      tpl2stmts(tpl)
    }
    if (txMetaElements.nonEmpty) {
      val txMetaStmts = (new Save(true) with Save_stmts).getRawStmts(txMetaElements, datomicTx)
      stmts.addAll(txMetaStmts)
    }
    Collections.unmodifiableList(stmts)
  }

  override protected def addComposite(
    n: Int,
    elements: Seq[Element]
  ): Product => Unit = {
    hasComposites = true
    val composite2stmts = getResolver(elements)
    // Start from initial entity id for each composite sub group
    elements.length match {
      case 1 => (tpl: Product) =>
        e = e0
        composite2stmts(Tuple1(tpl.productElement(n)))
      case _ => (tpl: Product) =>
        e = e0
        composite2stmts(tpl.productElement(n).asInstanceOf[Product])
    }
  }

  override protected def addNested(
    n: Int,
    ns: String,
    refAttr: String,
    elements: Seq[Element]
  ): Product => Unit = {
    val nested2stmts = getResolver(elements)
    val nestedArity  = elements.count {
      case _: Mandatory@unchecked => true
      case _: Optional@unchecked  => true
      case _                      => false
    }
    nestedArity match {
      case 1 =>
        (tpl: Product) => {
          val nested       = tpl.productElement(n).asInstanceOf[Seq[Any]]
          val nestedBaseId = e
          nested.foreach { value =>
            e = nestedBaseId
            val tpl = Tuple1(value)
            addRef(ns, refAttr)(tpl)
            e0 = e
            nested2stmts(tpl)
          }
        }
      case _ =>
        (tpl: Product) => {
          val nested       = tpl.productElement(n).asInstanceOf[Seq[Product]]
          val nestedBaseId = e
          nested.foreach { tpl =>
            e = nestedBaseId
            addRef(ns, refAttr)(tpl)
            e0 = e
            nested2stmts(tpl)
          }
        }
    }
  }

  override protected def addV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      appendStmt(add, e, a, value(tpl.productElement(n)).asInstanceOf[AnyRef])
    }
  }
  override protected def addOptV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(n) match {
        case Some(v) =>
          appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
        case None    => // no statement to insert
      }
    }
  }
  override protected def addTxV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      e = datomicTx
      backRefs = backRefs + (ns -> e)
      appendStmt(add, e, a, value(tpl.productElement(n)).asInstanceOf[AnyRef])
    }
  }

  override protected def addSet(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(n).asInstanceOf[Set[Any]].foreach { v =>
        appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
      }
    }
  }
  override protected def addOptSet(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(n) match {
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

  override protected lazy val valueString     = identity
  override protected lazy val valueInt        = identity
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